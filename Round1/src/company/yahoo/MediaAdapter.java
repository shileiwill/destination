package company.yahoo;

public class MediaAdapter {
	Player player;
	
	MediaAdapter(String type) {
		if (type.equals("mp3")) {
			player = new Mp3Player();
		} else if (type.equals("mp4")) {
			player = new Mp4Player();
		}
	}
	
	void play(String song) {
		player.play(song);
	}
}
