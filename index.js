/**
 * Created by davidbuttar on 21/05/2016.
 */
// Setup basic express server
var express = require('express');
var app = express();
var server = require('http').createServer(app);
var port = process.env.PORT || 3003;

server.listen(port, function () {
    console.log('Server listening at port %d', port);
});

// Routing
app.use(express.static(__dirname + '/site'));

//open('http://localhost:3003');
