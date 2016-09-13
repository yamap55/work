const server = require('express')();
const pg = require('pg');

const con = "tcp://postgres:postgres@localhost/webapi-example";

server.get('/', function(req, res){
  const name = req.query.name;
  const password = req.query.pass;
  console.log("input name:" + name + ", password:" + password);
  if (!name || !password) {
    res.json({});
    return;
  }

  pg.connect(con, (err, client)=>{
    if (err) {
      console.log("error");
      console.log(err);
      return;
    }
    const query = client.query("select * from user_info where name = '" + name + "' and password = '" + password + "'",
    (e, client)=>{
      if (e) {
        console.log("error");
        console.log(err);
        return;
      }
      const mes = client.rows.length < 1 ? "id not found" : "success id:" + client.rows[0].id + ", name:" + client.rows[0].name;
      console.log(mes);
      res.header({"Access-Control-Allow-Origin":"*"});
      res.json(client.rows);
    });
  });
});
server.listen(3000);
