package boguszGroup.Blog.utils;

import java.util.Set;

public class Ids {

 static public int generateNewId(Set<Integer> keysSoFar){
    if(keysSoFar.isEmpty()){
      return 0;
    }
    else{
      Integer integer = keysSoFar.stream().max((o1, o2) -> o1.compareTo(o2)).get();
      return integer+1;
    }
  }
}
