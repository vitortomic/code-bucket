public static void main(String[] args) {
		int status = 0;
		URL url;
		try {
			url = new URL("http://localhost:9000");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			status = con.getResponseCode();
		} catch (Exception e) {
			// @sm Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print(status);
	}
