module.exports = function(robot) {
    robot.respond(/hoge/i, function(msg){
      msg.send("hoge");
        // var today = new Date();
        //
        // msg.reply(today.getDay() === 0 || today.getDay() === 6 ? "YES" : "NO");
    });
}
