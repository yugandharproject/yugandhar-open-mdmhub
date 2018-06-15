package com.yugandhar.common.util;
/* Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Base64;

/**
 * Yugandhar Encoder and Decoder utility. The base64 encoder is used to encode the string
 * @author Yugandhar
 * @version 2.0
 * @since 2.0
 * @see Documentation
 */

public class YugandharEncoderDecoder {
	
	
	/**
	 * This method encodes the input string
	 * 
	 * @since 1.0
	 * @param String input String to be encoded. The base64 encoder is used to encode the string
	 * @return String encoded string as per base64 encoding
	 *
	 */

	public String encodeString(String inputString){
	
		String encodedString = Base64.getEncoder().encodeToString(inputString.getBytes());
		return encodedString;
	}
	
	
	/**
	 * This method decodes the input string
	 * 
	 * @since 1.0
	 * @param String input String to be decoded. The base64 decoder is used to decode the string
	 * @return String decoded string as per base64 encoding
	 *
	 */
	public String decodeString(String inputString){
		
		byte[] decodedBytes = Base64.getDecoder().decode(inputString);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	
	
	/**
	 * This is utility main method to encode or decode the string
	 * YugandharEncoderDecoder Arguments:
	 * 1: Required: operation name			valid values 'encode/decode'
	 * 2: Required: input String			valid values: any UTF-8 complaint String
	 * Example: YugandharEncoderDecoder encode mypassword
	 * Example: YugandharEncoderDecoder decode bXlwYXNzd29yZA==

	 * @since 2.0
	 * @param String[] String array having Operation Name and String to be encoded/decoded
	 */
	
	public static void main(String[] args) {
		
		if (args.length < 2) {
        	showUsage();	
        	System.exit(0);
        }
		
		String operationName = args[0];
		String inputString = args[1];
		
		YugandharEncoderDecoder yugandharEncoderDecoder= new YugandharEncoderDecoder();
		
		if(operationName.equalsIgnoreCase("encode")){
			System.out.println("Encoded String:" + yugandharEncoderDecoder.encodeString(inputString));
		} else if(operationName.equalsIgnoreCase("decode")){
			System.out.println("Decoded String:" + yugandharEncoderDecoder.decodeString(inputString));
		} else {
			System.out.println("Error: Operation name not recognized");
			showUsage();	
		}
	

	}
	
	
	/**
     * Shows the usage of this utility.
     */
    private static void showUsage() {
    	System.out.println("-----------------------------------------------------------------");
    	System.out.println("YugandharEncoderDecoder Arguments:\n");
    	System.out.println("1: Required: operation name			valid values 'encode/decode'");
    	System.out.println("2: Required: input String			valid values: any UTF-8 complaint String");
    	System.out.println("Example: YugandharEncoderDecoder encode mypassword");
    	System.out.println("Example: YugandharEncoderDecoder decode bXlwYXNzd29yZA==");
    	System.out.println("-----------------------------------------------------------------");
    }

}
