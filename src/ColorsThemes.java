import javafx.scene.paint.Color;

/**
 * Artem Voytenko
 * 30.11.2018
 */

// перечисление пар цветов
public enum ColorsThemes {
	ONE(Color.web("#2b2b2b"), Color.web("#3c3f41")),
	TWO(Color.web("#595775"), Color.web("#aba6bf")),
	THREE(Color.web("#132226"), Color.web("#525b56")),
	FOUR(Color.web("#282726"), Color.web("#6a8a82")),
	FIVE(Color.web("#595775"), Color.web("#aba6bf")),
	SIX(Color.web("#5d535e"), Color.web("#9a9eab")),
	SEVEN(Color.web("#2d4262"), Color.web("#73605b")),
	EIGHT(Color.web("#626d71"), Color.web("#cdcdc0"));

	private Color backgroundColor;
	private Color textColor;

	// приватный конструктор
	ColorsThemes(Color backgroundColor, Color textColor) {
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
	}

	// геттеры полей инама
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getTextColor() {
		return textColor;
	}
}
