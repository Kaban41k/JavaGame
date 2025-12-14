import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Обработчик пользовательского ввода с клавиатуры
 */
public class InputHandler implements KeyListener {
    private GameController gameController;

    // Множество клавиш, которые в данный момент нажаты
    private Set<String> pressedKeys;

    // Множество клавиш, которые были только что нажаты (для разовых действий)
    private Set<String> justPressedKeys;

    // Маппинг кодов клавиш на строковые идентификаторы
    private Map<Integer, String> keyMapping;

    /**
     * Конструктор
     * @param gameController ссылка на игровой контроллер
     */
    public InputHandler(GameController gameController) {
        this.gameController = gameController;
        this.pressedKeys = new HashSet<>();
        this.justPressedKeys = new HashSet<>();
        this.keyMapping = new HashMap<>();
        // Инициализируем маппинг клавиш
        initKeyMapping();
    }

    /**
     * Инициализирует маппинг клавиш
     */
    private void initKeyMapping() {
        // Стрелки
        keyMapping.put(KeyEvent.VK_UP, "UP");
        keyMapping.put(KeyEvent.VK_DOWN, "DOWN");
        keyMapping.put(KeyEvent.VK_LEFT, "LEFT");
        keyMapping.put(KeyEvent.VK_RIGHT, "RIGHT");

        // WASD
        keyMapping.put(KeyEvent.VK_W, "W");
        keyMapping.put(KeyEvent.VK_A, "A");
        keyMapping.put(KeyEvent.VK_S, "S");
        keyMapping.put(KeyEvent.VK_D, "D");

        // Действия
        keyMapping.put(KeyEvent.VK_SPACE, "SPACE");
        keyMapping.put(KeyEvent.VK_ENTER, "ENTER");
        keyMapping.put(KeyEvent.VK_ESCAPE, "ESCAPE");
        keyMapping.put(KeyEvent.VK_SHIFT, "SHIFT");
        keyMapping.put(KeyEvent.VK_CONTROL, "CTRL");

        // Функциональные клавиши
        keyMapping.put(KeyEvent.VK_P, "P");
        keyMapping.put(KeyEvent.VK_R, "R");
        keyMapping.put(KeyEvent.VK_Q, "Q");
        keyMapping.put(KeyEvent.VK_E, "E");
    }

    /**
     * Проверяет, нажата ли клавиша в данный момент
     * @param key строковый идентификатор клавиши
     * @return true если клавиша нажата
     */
    public boolean isKeyPressed(String key) {
        return pressedKeys.contains(key) || justPressedKeys.contains(key);
    }

    /**
     * Проверяет, была ли клавиша только что нажата (разовое нажатие)
     * @param key строковый идентификатор клавиши
     * @return true если клавиша была только что нажата
     */
    public boolean isKeyJustPressed(String key) {
        return justPressedKeys.contains(key);
    }

    /**
     * Сбрасывает состояние разовых нажатий
     */
    public void reset() {
        justPressedKeys.clear();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = keyMapping.get(e.getKeyCode());

        if (key != null) {
            // Если клавиша ещё не была нажата, добавляем в justPressed
            if (!pressedKeys.contains(key)) {
                justPressedKeys.add(key);
            }
            pressedKeys.add(key);

            // Обработка специальных клавиш
            handleSpecialKeys(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = keyMapping.get(e.getKeyCode());

        if (key != null) {
            pressedKeys.remove(key);
        }
    }

    /**
     * Обрабатывает специальные клавиши (пауза, выход и т.д.)
     * @param key строковый идентификатор клавиши
     */
    private void handleSpecialKeys(String key) {
        switch (key) {
            case "ESCAPE":
            case "P":
                // Пауза
                gameController.togglePause();
                break;

            case "R":
                // Перезапуск игры
                if (gameController.isPaused()) {
                    gameController.restartGame();
                }
                break;

            case "Q":
                // Выход из игры
                if (gameController.isPaused()) {
                    gameController.stopGame();
                }
                break;
        }
    }
    /**
     * Очищает все нажатые клавиши
     */
    public void clearAll() {
        pressedKeys.clear();
        justPressedKeys.clear();
    }
    /**
     * Возвращает множество всех нажатых клавиш
     * @return множество нажатых клавиш
     */
    public Set<String> getPressedKeys() {
        return new HashSet<>(pressedKeys);
    }
}