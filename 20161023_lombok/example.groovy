import groovy.transform.ToString  
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@ToString
class Bean {
  int id
  String name
}

// Getter, Setter
def bean = new Bean() 
bean.id = 10
bean.name = "�ق�"

// @ToString
assert bean.toString() == "Bean(10, �ق�)"

def bean2 = new Bean()
bean2.id = 10
bean2.name = "�ق�"

// @EqualsAndHashCode
assert bean == bean2

// AllArgsConstructor
def bean3 = new Bean([id:20,name:"�ӂ�"])
assert bean3.toString() == "Bean(20, �ӂ�)"