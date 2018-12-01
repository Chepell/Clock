import java.time.LocalDateTime;

/**
 * Artem Voytenko
 * 30.11.2018
 */

// класс для получения значений текущего времени
public class Clock {

	// метод возвращает текущие часы
	public String timeHH() {
		int hourLast = LocalDateTime.now().getHour();
		return alwaysShowTwoDigits(hourLast);
	}

	// метод возвращает текущие минуты
	public String timeMM() {
		int minuteLast = LocalDateTime.now().getMinute();
		return alwaysShowTwoDigits(minuteLast);
	}

	// метод возвращает текущие секунды
	public String timeSS() {
		int secondLast = LocalDateTime.now().getSecond();
		return alwaysShowTwoDigits(secondLast);
	}

	// метод ставит 0 слева от единственной цифры в числе
	private String alwaysShowTwoDigits(int num) {
		String numStr = num + "";
		if (numStr.length() == 1) {
			numStr = "0" + numStr;
		}
		return numStr;
	}
}
