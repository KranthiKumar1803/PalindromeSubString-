package com.assign.palindromicsubstring.service;

import com.assign.palindromicsubstring.entity.PalindromePO;
import com.assign.palindromicsubstring.repository.PalindromeRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Below class is used for finding longest palindrome substring
 * from given string
 */
@Service
public class PalindromeFinder {

    private PalindromeRepository palindromeRepository;

    public PalindromeFinder(PalindromeRepository palindromeRepository) {
        this.palindromeRepository = palindromeRepository;
    }
    /**
     * Consider each character as midpoint and expand around its center
     * 1 . Even length and odd length palindromes are formed using above approach
     * 2 . Max is picked among both
     * @param input
     * @return
     */
    public String findLongestPalindromicSubString(String input) {

        int maxLength = 1;
        int start = 0;
        int low,high;

        for (int index = 1; index < input.length(); ++index) {
            //even length palindrome
            low = index - 1;
            high = index;
            int len = input.length();

            while (low >= 0 && high < len && input.charAt(low) == input.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }

            //odd length palindrome
            low = index - 1;
            high = index + 1;
            while (low >= 0 && high < len && input.charAt(low) == input.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }
        }
        return savePalindromeStringIfLarger(input, maxLength, start);
    }

    /**
     * Below method saves data to underlying database
     * @param input
     * @param maxLength
     * @param start
     */
    private String savePalindromeStringIfLarger(String input, int maxLength, int start) {
        PalindromePO existingPalindromePO = null;
        String currentPalindromeString = input.substring(start, start + maxLength);
        Iterable<PalindromePO> palindromeStrings = palindromeRepository.findAll();
        Iterator<PalindromePO> iterator = palindromeStrings.iterator();
        while (iterator.hasNext()) {
            existingPalindromePO = iterator.next();
        }
        if (null == existingPalindromePO) {
            PalindromePO palindromePO = new PalindromePO();
            palindromePO.setPalindromeString(currentPalindromeString);
            palindromeRepository.save(palindromePO);
            return palindromePO.getPalindromeString();
        }

        if (existingPalindromePO.getPalindromeString().length() >= currentPalindromeString.length()) {
            return existingPalindromePO.getPalindromeString();
        }

        if (existingPalindromePO.getPalindromeString().length() < currentPalindromeString.length()) {
            existingPalindromePO.setPalindromeString(currentPalindromeString);
            palindromeRepository.save(existingPalindromePO);
        }
        return null;
    }

    /**
     * Below method fetches palindromic substring from In Memory database
     * @return
     */
    public String getLargestPalindromicString() {
        Iterable<PalindromePO> palindromeStrings = palindromeRepository.findAll();
        Iterator<PalindromePO> iterator = palindromeStrings.iterator();
        while (iterator.hasNext()) {
            PalindromePO palindromePO = iterator.next();
            return palindromePO.getPalindromeString();
        }
        return "There is no palindrome string saved";
    }
}
