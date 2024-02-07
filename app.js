// app.js
const express = require('express');
const path = require('path');

const app = express();
const port = 4000; // Adjust the port if needed

const request = require('request');


// Serve static files from the "public" directory
app.use(express.static(path.join(__dirname, 'public')));

// Set up the homepage route
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.get('/spotify', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'spotify.html'));
});

app.get('/book', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'book.html'));
});

app.get('/script.js', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', '/public/script.js'));
});

app.get('/spotify.js', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', '/public/spotify.js'));
});

// Start the server
app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}`);
});
