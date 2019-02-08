package meTube.util;

public final class YouTubeLinkParser {

    private YouTubeLinkParser() {
    }

    public static String getYouTubeID(String youTubeLink) {
        if (youTubeLink.contains("v=")) {
            String query = youTubeLink.split("\\?")[1];
            String[] queryParams = query.split("&");

            for (String kvp : queryParams) {
                String[] queryTokens = kvp.split("=");

                if (queryTokens[0].equals("v")) {
                    return queryTokens[1];
                }
            }
        }

        String[] linkTokens = youTubeLink.split("/");
        return linkTokens[linkTokens.length - 1];
    }
}
