# Konfiguration Reverse-Proxy

Es wird **nginx** als Reverse-Proxy der App vorgeschalten.
Dazu muss in `/etc/nginx/sites-available/default` auf dem Server 
folgender Abschnitt eingefügt werden:


```
 location /mastermind {
      proxy_set_header   X-Real-IP          $remote_addr;
      proxy_set_header   X-Forwarded-For    $proxy_add_x_forwarded_for;
      proxy_set_header   X-Forwarded-Host   $http_host;
      proxy_set_header   X-Forwarded-Proto  $scheme;
      proxy_set_header   X-Forwarded-Port   $server_port;
      proxy_set_header   X-Forwarded-Prefix /mastermind;

      proxy_pass http://127.0.0.1:8282/;

      rewrite ^([^.]*[^/])$ $1/ permanent;

  }

```
