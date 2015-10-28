package backend;

public enum ZustandEnum { Stein {
		@Override
		public String toString() {

			return "Stein";
		}
	}

	,
	
	Dame {
		@Override
		public String toString() {

			return "Dame";
		};
	}
}
