var google_query = require('../controllers/googleController');
var bing_query = require('../controllers/bingController');
var search_query = require('../controllers/searchController');
var ta_control = require('../controllers/textAnalysisControllers');

module.exports = function(app,config) {


  app.get('/api/googlesearch/:query',google_query.getGoogleSearchResults);
  app.get('/api/bingsearch/:query',bing_query.getBingSearchResults);
  app.get('/api/search/:query',search_query.getQuerySearchResults);
  app.get('/api/queryexpansion/:query',search_query.getExpansionSearchResults);
  app.get('/api/clustering/:query',search_query.getClusterSearchResults);
  app.post('/api/find-entity',ta_control.getEntityResults);
  app.post('/api/find-topics',ta_control.getTopicResults);
  app.post('/api/find-sentiments',ta_control.getSentimentResults);
  app.get('/api/sentiment-level',ta_control.getSentimentLevel);

  app.get('/partials/*', function(req, res) {
    res.render('../../public/app/' + req.params[0]);
  });

  app.all('/api/*', function(req, res) {
    res.send(404);
  });

  app.get('*', function(req, res) {
    //res.sendfile(config.rootPath +'server/views/index.html');
      res.render('index', { title: 'ejs' });
  });
}