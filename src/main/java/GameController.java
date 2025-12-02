import model.GameManager;
import view.GameWindow;

/**
 * Главный контроллер игры - управляет взаимодействием между Model и View
 */
public class GameController {

    private GameManager gameManager;
    private GameWindow gameWindow;
    private InputHandler inputHandler;
    private boolean isRunning;
    private boolean isPaused;

    // FPS настройки
    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    /**
     * Конструктор контроллера
     */
    public GameController() {
        // Инициализируем Model
        this.gameManager = new GameManager();

        // Инициализируем View
        this.gameWindow = new GameWindow();

        // Инициализируем обработчик ввода и связываем его с окном
        this.inputHandler = new InputHandler(this);
        this.gameWindow.addKeyListener(inputHandler);

        this.isRunning = false;
        this.isPaused = false;
    }

    /**
     * Обрабатывает пользовательский ввод
     */
    public void handleInput() {
        // Проверяем нажатия клавиш через InputHandler
        if (inputHandler.isKeyPressed("SPACE")) {
            // Пример: стрельба
            gameManager.playerShoot();
        }

        if (inputHandler.isKeyPressed("W") || inputHandler.isKeyPressed("UP")) {
            gameManager.movePlayer(0, -1);
        }

        if (inputHandler.isKeyPressed("S") || inputHandler.isKeyPressed("DOWN")) {
            gameManager.movePlayer(0, 1);
        }

        if (inputHandler.isKeyPressed("A") || inputHandler.isKeyPressed("LEFT")) {
            gameManager.movePlayer(-1, 0);
        }

        if (inputHandler.isKeyPressed("D") || inputHandler.isKeyPressed("RIGHT")) {
            gameManager.movePlayer(1, 0);
        }

        // Сброс состояния разовых клавиш
        inputHandler.reset();
    }

    /**
     * Обновляет состояние игры
     */
    public void updateGame() {
        if (!isPaused) {
            gameManager.tick();
        }
    }

    /**
     * Запускает игру
     */
    public void startGame() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        isPaused = false;

        gameWindow.init();

        gameLoop();
    }

    /**
     * Ставит игру на паузу
     */
    public void pauseGame() {
        isPaused = true;
    }

    public void resumeGame() {
        isPaused = false;
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public void stopGame() {
        isRunning = false;
    }

    private void gameLoop() {
        long lastLoopTime = System.nanoTime();

        while (isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            handleInput();

            updateGame();

            gameWindow.update();

            if (gameManager.isGameOver()) {
                handleGameOver();
            }

            // Синхронизация FPS
            try {
                long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Закрываем окно при выходе
        gameWindow.dispose();
    }

    /**
     * Обрабатывает окончание игры
     */
    private void handleGameOver() {
        pauseGame();
        // Здесь можно добавить отображение экрана Game Over
        System.out.println("Game Over! Score: " + gameManager.getScore());
    }

    /**
     * Перезапускает игру
     */
    public void restartGame() {
        gameManager.reset();
        isPaused = false;
    }

    // Геттеры для доступа к компонентам
    public GameManager getGameManager() {
        return gameManager;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isRunning() {
        return isRunning;
    }
}