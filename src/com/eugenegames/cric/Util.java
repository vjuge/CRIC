package com.eugenegames.cric;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import android.util.Patterns;

public final class Util {

	/**
	 * calculate the IMC = weight / tall²
	 * 
	 * @param taille
	 * @String. if String is empty, initialize value to 0
	 * @param poids
	 * @String. if String is empty, initialize value to 0
	 * @return String rounded, if poids equals 0, returns the String "IMC"
	 */
	public static String calcIMC(String taille, String poids) {
		float t, p;
		try {
			t = Float.valueOf(taille);
		} catch (NumberFormatException e) {
			t = 0;
		}
		try {
			p = Float.valueOf(poids);
		} catch (NumberFormatException e) {
			p = 0;
		}
		// avoid division by 0
		if (t == 0) {
			// Toast.makeText(this, "taille trop petite", Toast.LENGTH_SHORT)
			// .show();
			return "IMC";
		} else {
			return String.valueOf(Math.round(p / (float) Math.pow(t, 2)));
		}
	}

	public static class IpCalculator {
		private Long mask, ip, wildcard;

		public String getMask() {
			return IpCalculator.convertDecimalToIpFormat(mask);
		}

		public String getIp() {
			return IpCalculator.convertDecimalToIpFormat(ip);
		}

		public String getWildcard() {
			return IpCalculator.convertDecimalToIpFormat(wildcard);
		}

		/**
		 * will convert IP and mask into decimal values
		 * 
		 * @param IP
		 *            String like 192.168.0.1
		 * @param Mask
		 *            String value like 24 (for /24)
		 */
		public IpCalculator(String IP, String Mask) {

			ip = convertStringToDecimalIpFormat(IP);
			mask = convertStringToDecimalMaskIpFormat(Mask);
			wildcard = convertStringToDecimalWildcardIpFormat(Mask);

		}

		public static String convertDecimalToIpFormat(Long addressL) {
			String[] ret = new String[4];
			Long c = Long.valueOf(16777216L);
			for (int i = 0; i < 4; i++) {
				long k = (long) addressL / c;
				addressL -= c * k;
				ret[i] = String.valueOf(k);
				c /= 256L;
			}
			return ret[0] + "." + ret[1] + "." + ret[2] + "." + ret[3];
		}

		/**
		 * @param addressS
		 *            String value like 192.168.0.1
		 * @return
		 */
		public static Long convertStringToDecimalIpFormat(String addressS) {
			long[] ipTokens = new long[4];
			long ip;
			final String DELIM = "[.]";
			ipTokens[0] = Long.valueOf(addressS.split(DELIM)[0]).intValue();
			ipTokens[1] = Long.valueOf(addressS.split(DELIM)[1]).intValue();
			ipTokens[2] = Long.valueOf(addressS.split(DELIM)[2]).intValue();
			ipTokens[3] = Long.valueOf(addressS.split(DELIM)[3]).intValue();
			ip = (ipTokens[0] * 16777216) + (ipTokens[1] * 65536)
					+ (ipTokens[2] * 256) + ipTokens[3];
			return Long.valueOf(ip);
		}

		/**
		 * @param maskS
		 *            String value like 24 (for /24)
		 * @return
		 */
		public static Long convertStringToDecimalMaskIpFormat(String maskS) {
			long mask;
			mask = (long) (Math.pow(2, 32 - Long.valueOf(maskS).longValue()) - 1);
			// 4294967295L = 0xFFFFFFFF, 2complement
			mask = Long.valueOf(4294967295L) - mask;
			return Long.valueOf(mask);
		}

		/**
		 * @param maskS
		 *            String value like 24 (for /24) return the 2complement of
		 *            the mask (wildcard) i.e 0x000000FF
		 * @return
		 */
		public static Long convertStringToDecimalWildcardIpFormat(String maskS) {
			long Wildcard;
			Wildcard = (long) (Math
					.pow(2, 32 - Long.valueOf(maskS).longValue()) - 1);
			return Long.valueOf(Wildcard);
		}

		/**
		 * @return
		 */
		public String getNetworkAddress() {
			return convertDecimalToIpFormat(ip & mask);
		}

		/**
		 * @return
		 */
		public String getBroadCast() {
			return convertDecimalToIpFormat((ip & mask) + wildcard);
		}

		/**
		 * @return
		 */
		public String getFirstMachine() {
			return "";
		}

		/**
		 * @return
		 */
		public String getLastMachine() {
			return "";
		}

		/**
		 * @return
		 */
		public long getNumOfMachines() {
			Long r = Long.valueOf(4294967295L - mask);
			return (long) r - 1;
		}
	}

	/**
	 * calculate if input String is a well-formatted IPv4 address
	 * 
	 * @param s
	 *            String
	 * @return true if valid IPv4 address
	 */
	public static boolean isIPAddress(String s) {
		final Pattern sIPPattern = Patterns.IP_ADDRESS;
		// keep code for older Android SDK under v8
		// final Pattern sIPPattern = Pattern
		// .compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
		// +
		// "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
		// +
		// "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
		// + "|[1-9][0-9]|[0-9]))");

		return sIPPattern.matcher(s).matches();

	}

	/**
	 * calculate if input String is a valid IP Mask (CIDR)
	 * 
	 * @param s
	 *            input String
	 * @return true if valid
	 */
	public static boolean isIPMask(String s) {
		try {
			int m = Integer.valueOf(s);
			if (m > 0 && m < 33) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}

	}

}
