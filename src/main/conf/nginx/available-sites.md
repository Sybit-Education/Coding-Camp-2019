# Konfiguration Reverse-Proxy

Es wird **nginx** als Reverse-Proxy der App vorgeschalten.
Dazu muss in `/etc/nginx/sites-available/default` auf dem Server 
folgender Abschnitt eingefügt werden:


```
  location /mastermind {
      proxy_set_header   X-Real-IP         $remote_addr;
      proxy_set_header   X-Forwarded-For   $proxy_add_x_forwarded_for;
      proxy_set_header   X-Forwarded-Host  $http_host;
      proxy_set_header   X-Forwarded-Proto $scheme;
      proxy_set_header   X-Forwarded-Port  $server_port;

      proxy_redirect off;
      proxy_intercept_errors on;

      # WebSocket support
      proxy_http_version 1.1;
      proxy_set_header   Upgrade           $http_upgrade;
      proxy_set_header   Connection        "Upgrade";
      proxy_set_header   Host              $host;
      proxy_set_header   Origin            ''; # avoid 403 error see: https://git.io/v7frj
      proxy_read_timeout 86400;

      proxy_pass http://127.0.0.1:8282/;
      rewrite ^([^.]*[^/])$ $1/ permanent;
  }
```
