# gen_code

Generate code project for AndroidStudio as java project.

## Get Start

1. Add template file:
    
    ```java
    R little_camel_case
    R big_camel_case
    R lease
    
    I 2
    
    //
    /**
     * Returns {$param_name.lease}.
     */
    public {$param_type} get{$param_name.big_camel_case}() {
      return this.{$param_name.little_camel_case};
    }
    
    /**
     * Set {$param_name.lease}.
     *
     * @param {$param_name.little_camel_case} {$param_name.lease}.
     */
    public void set{$param_name.big_camel_case}({$param_type} {$param_name.little_camel_case}) {
      this.{$param_name.little_camel_case} = {$param_name.little_camel_case};
    }
    ```
    
2. Add param file:
    
    ```
    0 param_name
    1 param_type
    
    S @
    
    E #
    
    //
    name String
    age int
    ```

3. Add execute class:
    
    ```java

    @TemplatePath("templates/getter_and_setter")
    @ParamPath("params/getter_and_setter_params")
    public class GetterAndSetter {
    
    public static void main(String[] args) {
        ExportUtils.execSimple(GetterAndSetter.class);
      }
    }

    ```
    
4. Run execute class:
    
    ```java
    
    "C:\dev\Android\Android Studio\jre\bin\java"...
      /**
       * Returns name.
       */
      public String getName() {
        return this.name;
      }
      
      /**
       * Set name.
       *
       * @param name name.
       */
      public void setName(String name) {
        this.name = name;
      }
    
      /**
       * Returns age.
       */
      public int getAge() {
        return this.age;
      }
      
      /**
       * Set age.
       *
       * @param age age.
       */
      public void setAge(int age) {
        this.age = age;
      }
    
    
    Process finished with exit code 0
    
    ```
    
5. Copy gen result to where you need it.

## Template

Add rules:

```

R little_case
R CopyTwice com.sample.gen_rules.CopyTwice

```

Add Indent:

```

I 2

```

Template Begin:

```

//

```

## Params

Add param map:

```

0 param_name
1 param_type

```

Add word separator:

```

S @

```

Add empty param place holder:

```

E #

```

Template Begin:

```

//

```

## License

```

   Copyright 2017 VerstSiu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```