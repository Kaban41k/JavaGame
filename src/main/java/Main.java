import model.GameManager;
import view.GameWindow;

/**
 * Главный класс приложения - точка входа в программу
 */
public class Main {

    public static void main(String[] args) {
        // Создаём экземпляр игрового контроллера
        GameController gameController = new GameController();

        // Запускаем игру
        gameController.startGame();
    }
}