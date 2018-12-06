var ws;//websocket实例
var lockReconnect = false;//避免重复连接
var msgCallBack = {};
var wsUrl = "";
var lastTime = -1;

function createWebSocket(wsUrl) {
    try {
        ws = new WebSocket(wsUrl);
        initEventHandle();
    } catch (e) {
        reconnect(wsUrl);
    }
}

function createConnection(fansId, onSuccess, onError, onClose, onMessage) {
    if (onSuccess) {
        msgCallBack.onSuccess = onSuccess;
    }
    if (onError) {
        msgCallBack.onError = onError;
    }
    if (onClose) {
        msgCallBack.onClose = onClose;
    }
    if (onMessage) {
        msgCallBack.onMessage = onMessage;
    }
    wsUrl = "ws://127.0.0.1:9091/?type=2&id=" + fansId;
    createWebSocket(wsUrl);

}

function initEventHandle() {
    ws.onclose = function () {
        console.log("onclose");
        msgCallBack.onClose();
        reconnect(wsUrl);
    };
    ws.onerror = function () {
        console.log("onerror");
        msgCallBack.onError();
        reconnect(wsUrl);
    };
    ws.onopen = function () {
        //心跳检测重置
        console.log("onopen");
        msgCallBack.onSuccess();
        heartCheck.reset().start();
    };
    ws.onmessage = function (event) {
        //如果获取到消息，心跳检测重置
        //拿到任何消息都说明当前连接是正常的
        if (event.data === '@heart') {
            var nowTime = new Date().getTime();
            console.log(nowTime - lastTime)
            if (lastTime !== -1 && nowTime - lastTime > 15000) {
                reconnect(wsUrl);
                console.log("后台返回心跳时间间隔异常");
            }
            lastTime = nowTime;
            console.log("服务器心跳消息:" + event.data);
        } else {
            console.log('接收chat-server消息: ' + JSON.parse(event.data).content);
            msgCallBack.onMessage(JSON.parse(event.data));
            msgCallBack.callBack && msgCallBack.callBack.call(null, JSON.parse(event.data));
        }
        heartCheck.reset().start();
    }
}

function reconnect(url) {
    if (lockReconnect) return;
    lockReconnect = true;
    //没连接上会一直重连，设置延迟避免请求过多
    setTimeout(function () {
        createWebSocket(url);
        lockReconnect = false;
    }, 2000);
}


//心跳检测
var heartCheck = {
    timeout: 15000,//15秒
    timeoutObj: null,
    serverTimeoutObj: null,
    reset: function () {
        clearTimeout(this.timeoutObj);
        clearTimeout(this.serverTimeoutObj);
        return this;
    },
    start: function () {
        var self = this;
        this.timeoutObj = setTimeout(function () {
            //这里发送一个心跳，后端收到后，返回一个心跳消息，
            //onmessage拿到返回的心跳就说明连接正常
            ws.send("@heart");
            self.serverTimeoutObj = setTimeout(function () {//如果超过一定时间还没重置，说明后端主动断开了
                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
            }, self.timeout)
        }, this.timeout)
    }
}


function sendMsg(sendData) {
    console.log("调用发送方法")
    ws.send(JSON.stringify(sendData));
}

//回调函数 消息回显
function setMsgCallBack(callBack) {
    if (callBack) {
        msgCallBack.callBack = callBack;
    }
}