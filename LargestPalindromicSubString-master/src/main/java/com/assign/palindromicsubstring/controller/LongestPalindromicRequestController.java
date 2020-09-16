package com.assign.palindromicsubstring.controller;

import com.assign.palindromicsubstring.service.PalindromeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class which accepts incoming HttpRequest and routes to appropriate service
 * based on incoming request
 *
 */
@RestController
public class LongestPalindromicRequestController {

    public PalindromeFinder palindromeFinder;

    @Autowired
    public LongestPalindromicRequestController(PalindromeFinder palindromeFinder) {
        this.palindromeFinder = palindromeFinder;
    }

    /**
     * Below method saves largest palindromic substring in case if provided sub string is larger than
     * already exisitng in database
     * @param inputString
     * @return
     */
    @PostMapping("/find/palindrome")
    public String findLongestPalindrome(@RequestBody String inputString) {
        return String.format("Largest Palindrome string saved in Database is %s",
                palindromeFinder.findLongestPalindromicSubString(inputString));
    }

    /**
     * Below method fetches largest palindromic substring saved in In Memory database
     */
    @GetMapping("/get/largest/palindrome")
    public String getLargestPalindromeSubString() {
        return palindromeFinder.getLargestPalindromicString();
    }

    /**
     * Get Binary reversed format of given string
     */
    @PostMapping("/getBinaryMapping")
    public String binaryReversal(@RequestBody String str) {
        String binaryRep = Integer.toString(Integer.parseInt(str),2);
        int mod = (binaryRep.length())%8;
        int padLength = 8-mod;
        while (padLength-- > 0) {
            binaryRep = "0"+binaryRep;
        }
        //binaryRep = new StringBuilder().append(binaryRep).reverse().toString();
        int finalnum = 0;
        for (int i=0; i < binaryRep.length();i++) {
            if(binaryRep.charAt(i) == '1')
                finalnum += Math.pow(2,i)*1;
        }
        return finalnum+"";
    }


}
