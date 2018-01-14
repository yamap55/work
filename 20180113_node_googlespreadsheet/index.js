var GoogleSpreadsheet = require('google-spreadsheet');

var spreadsheet = new GoogleSpreadsheet('token');
var credentials = require('./secret/token.json');

var sheet;
spreadsheet.useServiceAccountAuth(credentials, function(err){
  spreadsheet.getInfo(function(err, sheets){
    const sheet = sheets.worksheets.find((s) => { return s.title == "記録"; });
    sheet.addRow( { "日付": 'あ', "時間":"b"},(err, row)=>{console.log(err + "aaaa" + row)});
  });
});
