'use strict';

// Update cache names any time any of the cached files change.
const CACHE_NAME = 'static-cache-v1';

// Add list of files to cache here.
const FILES_TO_CACHE = [
	'/mastermind/js/bootstrap.bundle.min.js',
	'/mastermind/js/bootstrap-colorselector.min.js',
	'/mastermind/js/jquery.min.js',

	'/mastermind/images/background.jpg',
	'/mastermind/images/logo.png',

	'/mastermind/css/bootstrap.min.css',
	'/mastermind/css/bootstrap-colorselector.min.css'
];

self.addEventListener('install', (evt) => {
	console.log('[ServiceWorker] Install');

	evt.waitUntil(
		caches.open(CACHE_NAME).then((cache) => {
			console.log('[ServiceWorker] Pre-caching offline page');
			return cache.addAll(FILES_TO_CACHE);
		})
	);


});

self.addEventListener('activate', (evt) => {
	console.log('[ServiceWorker] Activate');
	// Remove previous cached data from disk.

	evt.waitUntil(
		caches.keys().then((keyList) => {
			return Promise.all(keyList.map((key) => {
				if(key !== CACHE_NAME){
					console.log('[ServiceWorker] Removing old cache', key);
					return caches.delete(key);
				}
			}));
		})
	);
});

self.addEventListener('fetch', (evt) => {
	console.log('[ServiceWorker] Fetch', evt.request.url);
	if (evt.request.mode !== 'navigate') {
		// Not a page navigation, bail.
		return;
	}
	evt.respondWith(
		fetch(evt.request)
			.catch(() => {
				return caches.open(CACHE_NAME)
					.then((cache) => {
					return cache.match('offline.html');
				});
			})
	);

});
