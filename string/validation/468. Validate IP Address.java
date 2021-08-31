class Solution {
    public String validIPAddress(String IP) {
        if (IP.contains(":") && IP.contains(".")) {
            return "Neither";
        } else if (IP.contains(".")) {
            return validIPv4(IP);
        } else if (IP.contains(":")) {
            return validIPv6(IP);
        } else {
            return "Neither";
        }
    }
    
    private String validIPv4(String IP) {
        // only digits and 3 '.'
        if (IP.startsWith(".") || IP.endsWith(".") || !IP.matches("[0-9.]+")) {
            return "Neither";
        }
        String[] parts = IP.split("\\.");
        if (parts.length != 4) {
            return "Neither";
        }
        for (String part: parts) {
            if (part.length() == 0 || part.length() > 3) {
                return "Neither"; // a part that's too long cannot use Integer.parseInt
            }
            if (part.charAt(0) == '0' && part.length() > 1) {
                return "Neither"; // leading 0s
            }
            if (Integer.parseInt(part) > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }
    
    private String validIPv6(String IP) {
        if (IP.startsWith(":") || IP.endsWith(":") || !IP.matches("[0-9a-fA-F:]+")) {
            return "Neither";
        }
        String[] parts = IP.split("\\:");
        if (parts.length != 8) {
            return "Neither";
        }
        for (String part: parts) {
            if (part.length() == 0 || part.length() > 4) {
                 return "Neither";
            }
        }
        return "IPv6";
    }
}