class pseudoCode {
    public static void main(String[] args) {


        //获取客户端的 sid，也就是 myid
        if (sid < this.mySid) {
            //为了防止重复建立连接，只允许 sid 大的主动连接 sid 小的
            closeSocket(sock);//关闭连接
            connectOne(sid);//向 sid 发起连接
        } else {
            //同样，构建一个 SendWorker 和RecvWorker 进行发送和接收数据
            SendWorker sw = new SendWorker(sock, sid);
            RecvWorker rw = new RecvWorker(sock, din, sid, sw);
            sw.start();
            rw.start();
        }


    }
}