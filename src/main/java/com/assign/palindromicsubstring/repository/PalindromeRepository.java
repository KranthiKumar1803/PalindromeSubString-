package com.assign.palindromicsubstring.repository;

import com.assign.palindromicsubstring.entity.PalindromePO;
import org.springframework.data.repository.CrudRepository;

public interface PalindromeRepository extends CrudRepository<PalindromePO,Integer> {
}
