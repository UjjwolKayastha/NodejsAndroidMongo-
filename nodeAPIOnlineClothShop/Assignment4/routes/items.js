var express = require('express');
var items = require('../models/items');
var verify = require('../verify');
// var fs = require('fs');
var router = express.Router();

router.route('/')
    .get((req, res, next) => {
        items.find({})
            .then((items) => {
                res.statusCode = 200;
                res.setHeader('Content-Type', 'application/json');
                res.json(items);
            }, (err) => next(err))
            .catch((err) => next(err));
    })
    .post((req, res, next) => {
        items.create(req.body)
            .then((item) => {
                res.statusCode = 200;
                res.setHeader('Content-Type', 'application/json');
                res.json(item);
            }, (err) => next(err))
            .catch((err) => next(err));
    });

router.route('/:id')
    .get((req, res, next) => {
        items.findById(req.params.id)
            .then((item) => {
                res.statusCode = 200;
                res.setHeader('Content-Type', 'application/json');
                res.json(item);
            }, (err) => next(err))
            .catch((err) => next(err));
    })
   

module.exports = router;