function load() {
    const url = document.getElementById("url").value;
    const webviewEl = document.getElementById("foo");
    webviewEl.setAttribute("src", url);
}

function opendev() {
    const webview = document.getElementById("foo");
    webview.openDevTools();
}

const start = function() {
    let intervalFunc;
    let flag = false;
    return function() {
        const text = document.getElementById("timerbtn").innerText;
        if (flag) {
            clearInterval(intervalFunc);
        } else {
            intervalFunc = setInterval(timer, 1000);
        }
        document.getElementById("timerbtn").innerText = flag ? "Start" : "Stop";
        flag = !flag;
    }
}();

function timer() {
    const timeEl = document.getElementById("time");
    const i = parseIntForString(timeEl.value) - 1;
    timeEl.value = format(i);
}

function parseIntForString(s) {
    const m = s.match(/(\d\d):(\d\d)/);
    return m[1] * 60 + (m[2] * 1);
}

function format(a) {
    const m = ("0" + Math.floor(a / 60)).slice(-2);
    const s = ("0" + (a % 60)).slice(-2);
    return m + ":" + s;
}
