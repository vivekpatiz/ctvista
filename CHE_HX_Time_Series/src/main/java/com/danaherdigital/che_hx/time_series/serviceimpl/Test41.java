package com.danaherdigital.che_hx.time_series.serviceimpl;

import java.util.*;
import java.lang.*;
import java.io.*;
 
class Test41 
{
 
  // Recursively removes adjacent 
  // duplicates from str and
  // returns new string. las_removed 
  // is a pointer to
  // last_removed character
  private static String removeDuplicates(
    String s, char ch)
  {
 
    // If length of string is 1 or 0
    if (s == null || s.length() <= 1) 
    {
      return s;
    }
 
    int i = 0;
    while (i < s.length()) 
    {
      if (i + 1 < s.length()
          && s.charAt(i) == s.charAt(i + 1)) 
      {
        int j = i;
        while (j + 1 < s.length()
               && s.charAt(j) == 
               s.charAt(j + 1))
        {
          j++;
        }
        char lastChar
          = i > 0 ? s.charAt(i - 1) : ch;
 
        String remStr = removeDuplicates(
          s.substring(j + 1, s.length()),
          lastChar);
 
        s = s.substring(0, i);
        int k = s.length(), l = 0;
 
        // Recursively remove all the adjacent
        // characters formed by removing the
        // adjacent characters
        while (remStr.length() > 0 && 
               s.length() > 0 && 
               remStr.charAt(0) ==
               s.charAt(s.length() - 1)) 
        {
 
          // Have to check whether this is the
          // repeated character that matches the
          // last char of the parent String
          while (remStr.length() > 0
                 && remStr.charAt(0) != ch
                 && remStr.charAt(0)
                 == s.charAt(s.length()
                             - 1)) 
          {
            remStr = remStr.substring(
              1, remStr.length());
          }
          s = s.substring(0, s.length() - 1);
        }
        s = s + remStr;
        i = j;
      }
      else
      {
        i++;
      }
    }
    return s;
  }
 
  // Driver Code
  public static void main(String[] args)
  {
 
    String str1 = "mississipie";
    System.out.println(removeDuplicates(
      str1, ' '));
    String str2 = "ocvvcolop";
    System.out.println(removeDuplicates(
      str2, ' '));
  }
}