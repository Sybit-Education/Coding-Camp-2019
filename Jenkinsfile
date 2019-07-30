def dockerInstanceName = "coding-camp-2019";

def branchName = env.BRANCH_NAME.toLowerCase()
script {
    if (branchName.contains("/")) {
        // ignore branch type
        branchName = branchName.split("/")[1]
    }
}
branchName = branchName.replace("-", "")

def imageName = dockerInstanceName + ":" +
    ((env.BRANCH_NAME == "master") ? "" : "${branchName}-") +
    env.BUILD_ID

def dockerfile = "./docker/Dockerfile"
def customImage1
def customImage2

def remote = [:]
remote.name = "coding-camp"
remote.host = "coding-camp.sybit.de"

remote.allowAnyHosts = false
remote.user = "root"
//remote.password = "${PASSWORD}"
remote.identityFile = '/data/jenkins/secrets/keyfile'
remote.passphrase = '100%Hacker'
remote.knownHosts = '/data/jenkins/secrets/known_hosts'

pipeline {

  agent any

  stages {

    stage('Checkout'){
      steps {
        deleteDir() //cleanup workspace
        checkout scm
        sh 'chmod +x mvnw'
      }
    }

    stage('Build'){
      steps {
        mvnw 'install -DskipTests'
      }
    }


    stage('Test'){
      steps {
        mvnw 'test -Dspring.profiles.active=default'
        //mvnw 'sonar:sonar -Dsonar.host.url=http://lysithea.sybit.de:9000  -Dsonar.login=58d94fd942a8213454ef131105e188bbb2a57a63'
        step( [ $class: 'JacocoPublisher' ] )
      }
    }


    stage('Archive') {
      steps {
        archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/*.?ar', fingerprint: true
      }
    }

    stage('Docker build') {

        when {
            anyOf{
                branch 'develop'
                branch 'master'
            }
        }
        steps {
            script {

                customImage1 = docker.build("${dockerInstanceName}:${branchName}-${env.BUILD_NUMBER}", "-f ${dockerfile} .")
                customImage2 = docker.build("${dockerInstanceName}", "-f ${dockerfile} .")

                docker.withRegistry('https://coding-camp.artifactory.sybit.de', 'sybit_ausbildung_artifactory') {
                   customImage1.push("${branchName}-${env.BUILD_NUMBER}")
                   customImage2.push("latest")
                }
            }
        }
    }

    stage('Deploy Test') {
        when {
            branch 'develop'
        }
        steps {
            echo 'Deploy develop on Test-Server ...'
            sh 'docker-compose -f ./docker/docker-compose-TEST.yml up -d'
        }
    }

    stage('Deploy Production') {
        when {
            branch 'master'
        }
        steps {
            echo 'Deploy master on PROD-Server ...'

            withCredentials([sshUserPrivateKey(
                credentialsId: 'coding-camp.sybit.de',
                keyFileVariable: 'AAAAB3NzaC1yc2EAAAADAQABAAACAQDF6PI3dF+D8gSIyQe1Y/pSsJ9LxFmbFrzVnPIR1OGIdxON/OHIwWtDhs9tnSy+XbGXe88mJ2k/61iSymneHvG7lBRmRmLe9dQhYjn1rFGzCvDutpPjxrKl0U7vDV9dWChkCF3gF9kwax3ckDb54EFWqvg0imiasjA3d+CF+AD4KGTeGwfM3F+f9af0WxrZQmyfp1egePvRlexvhdL0kCFU4QJ2g6zEnNRUWDc3QzXdc9GPKIInWgrmbUH/7iDb2l1zAOYza5p4b0is0BR9NXbqeo9Q32kg891rjkw/aiWpzbTSMUqVWUb3j5HtLx+qLR5udfNKt9t+I1hjElMx+3WCEsyCA6ZV1DVLb03oEeXc7k9GUPYmMleaVxuq594GoVLusD98MHumvIHsDNiAuDjyya8QAhHMy08KCWSuUqnTjN+W8pNXF6l/Z1gDggNnuhijEYyhgCQnkB0w5qo/Ikpqm4AlpNcNNvgSWe0VgniJIk8mTWaMfK5cDL8XAAm8g7vi9KbJmUSZL4iFN382ICXCeDOl4y4FopHj5X3TLTHCijH6sfQfNsPYlrTarxet3xfssqHodIAABWWVXBtmUIp9t6UFRDg18JjbKgsRISEvWSS12mAhz52h0qvuKu/Zt4RgokH7aL4l4Rg1u2q8UCYIb/8xlbAwuN5jP5mGx9Ok8Q==',
                passphraseVariable: '',
                usernameVariable: 'userName'
                )]) {

                sshCommand remote: remote, command: 'uname -a'
                sshCommand remote: remote, command: 'docker-compose -f /home/docker/mastermind/docker-compose-PRODUCTION.yml down'
                sshCommand remote: remote, command: 'docker-compose -f /home/docker/mastermind/docker-compose-PRODUCTION.yml pull'
                sshCommand remote: remote, command: 'docker-compose -f /home/docker/mastermind/docker-compose-PRODUCTION.yml up -d'
            }
        }
    }


  }

  post {
    always {
        junit 'target/surefire-reports/*.xml'

    }
    failure {
        // Success or failure, always send notifications
        notifyBuild(currentBuild.result)
    }
  }


}

def mvnw(command) {

    if (isUnix()) {
        sh "./mvnw ${command}"
    } else {
        bat "mvnw.bat ${command}"
    }
}

def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>"""


  emailext (
      subject: subject,
      mimeType: 'text/html',
      body: details,
      attachLog: true,
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}
