var GoogleSpreadsheet = require('google-spreadsheet');

var spreadsheet = new GoogleSpreadsheet('token');
var credentials = require('./secret/token.json');

var sheet;
spreadsheet.useServiceAccountAuth(credentials, function(err){
  spreadsheet.getInfo(function(err, sheets){
    const sheet = sheets.worksheets.find((s) => { return s.title == "記録"; });
    const rowCount = sheet.rowCount
    console.log(rowCount);
    sheet.getRows({"offset":rowCount-1}, (err, rows)=>{
      console.log(rows[0]["日付"]);
      console.log((new Date("2018-01-29").getTime() - new Date(rows[0]["日付"]).getTime())/(1000*60*60*24));
    })
    // sheet.addRow( { "日付": 'あ', "時間":"b"},(err, row)=>{console.log(err + "aaaa" + row)});
  });
});
