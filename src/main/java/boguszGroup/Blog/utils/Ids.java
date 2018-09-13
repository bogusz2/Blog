package boguszGroup.Blog.utils;

import java.util.Set;

public class Ids {

 static public long generateNewId(Set<Long> keysSoFar){
    if(keysSoFar.isEmpty()){
      return 0;
    }
    else{
      long integer = keysSoFar.stream().max((o1, o2) -> o1.compareTo(o2)).get();
      return integer+1;
    }
  }
}
