package backend;

public enum FarbEnum {
	schwarz {
		@Override
		public String toString() {
			
			return "Schwarz";
		}
	}, weiß {
		@Override
		public String toString() {
			
			return "Weiß";
		}
	}, nichts;
	
}
