// config.js
var apiUrl;

if (window.location.hostname === 'production.example.com') {
    apiUrl = 'https://api.production.example.com';
} else if (window.location.hostname === 'staging.example.com') {
    apiUrl = 'https://api.staging.example.com';
} else {
    apiUrl = 'http://127.0.0.1:9091';
}