  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('service-worker.js')
      .then(() => console.log('service worker installed'))
      .catch(err => console.error('Error', err));
  }
  function addToHomeScreen() {
    let a2hsBtn = document.querySelector(".ad2hs-prompt");  // hide our user interface that shows our A2HS button
    a2hsBtn.style.display = 'none';  // Show the prompt
    deferredPrompt.prompt();  // Wait for the user to respond to the prompt
    deferredPrompt.userChoice
      .then(function(choiceResult){
    if (choiceResult.outcome === 'accepted') {
      console.log('User accepted the A2HS prompt');
    } else {
      console.log('User dismissed the A2HS prompt');
    }
    deferredPrompt = null;
  });}
  function showAddToHomeScreen() {
    let a2hsBtn = document.querySelector(".ad2hs-prompt");
    a2hsBtn.style.display = "block";
    a2hsBtn.addEventListener("click", addToHomeScreen);
  }
  let deferredPrompt;
  window.addEventListener('beforeinstallprompt', function (e) {
    // Prevent Chrome 67 and earlier from automatically showing the prompt
    e.preventDefault();
    // Stash the event so it can be triggered later.
    deferredPrompt = e;
    showAddToHomeScreen();
  });

  function showIosInstall() {
    let iosPrompt = document.querySelector(".ios-prompt");
    iosPrompt.style.display = "block";
    iosPrompt.addEventListener("click", () => {
      iosPrompt.style.display = "none";
    });
  }

  // Detects if device is on iOS
  const isIos = () => {
    const userAgent = window.navigator.userAgent.toLowerCase();
    return /iphone|ipad|ipod/.test( userAgent );
  }
  // Detects if device is in standalone mode
  const isInStandaloneMode = () => ('standalone' in window.navigator) && (window.navigator.standalone);
  // Checks if should display install popup notification:
  if (isIos() && !isInStandaloneMode()) {
    // this.setState({ showInstallMessage: true });
    showIosInstall();
  }
