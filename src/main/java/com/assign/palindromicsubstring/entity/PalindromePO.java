package com.assign.palindromicsubstring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PalindromePO {

    @Id
    @Column(updatable = false, nullable = false, unique = true)
    private int id;

    @Column(name = "palindrome_string")
    private String palindromeString;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getPalindromeString() { return palindromeString; }

    public void setPalindromeString(String palindromeString) { this.palindromeString = palindromeString; }
}
