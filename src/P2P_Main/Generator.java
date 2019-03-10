package P2P_Main;

import java.util.Random;

public class Generator {

	private static String[] words = { "Life is wonderful. Without it we'd all be dead.",
			"Daddy, why doesn't this magnet pick up this floppy disk?", "Give me ambiguity or give me something else.",
			"I.R.S.: We've got what it takes to take what you've got!",
			"We are born naked, wet and hungry. Then things get worse.",
			"Make it idiot proof and someone will make a better idiot.", "He who laughs last thinks slowest!",
			"Always remember you're unique, just like everyone else.",
			"A flashlight is a case for holding dead batteries.", "Lottery: A tax on people who are bad at math.",
			"Error, no keyboard - press F1 to continue.", "There's too much blood in my caffeine system.",
			"Artificial Intelligence usually beats real stupidity.",
			"Hard work has a future payoff. Laziness pays off now.",
			"Puritanism: The haunting fear that someone, somewhere may be happy.",
			"Consciousness: that annoying time between naps.",
			"Don't take life too seriously, you won't get out alive.",
			"I don't suffer from insanity. I enjoy every minute of it.",
			"Better to understand a little than to misunderstand a lot.", "The gene pool could use a little chlorine.",
			"When there's a will, I want to be in it.", "Okay, who put a \"stop payment\" on my reality check?",
			"We have enough youth, how about a fountain of SMART?", "Programming is an art form that fights back." };

	public static String generate() {

		Random rand = new Random();
		int ind = rand.nextInt(words.length);
		return words[ind];

	}

};
