public class PingPongGame implements Runnable {
    private int fps = 120;
    Thread gameLoopThread;
    GameFrame gameFrame;
    GamePanel gamePanel;

    public PingPongGame() {
        gamePanel = new GamePanel();
        gameFrame = new GameFrame(gamePanel);


        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / fps;
        //double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            //deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

//            if (deltaU >= 1) {
//                update();
//                updates++;
//                deltaU--;
//            }

            if (deltaF >= 1) {//fbs
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {//every 1 sec
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames );
                frames = 0;
                //updates = 0;
            }
        }
    }
}