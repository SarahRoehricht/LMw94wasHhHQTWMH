package backend;

public enum FarbEnum {
	schwarz {
		@Override
		public String toString() {
			
			return "schwarz";
		}
	}, weiß {
		@Override
		public String toString() {
			
			return "weiß";
		}
	};
	
}
