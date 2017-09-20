SET TEMPLATE_FILE=getter_and_setter
SET PARAM_FILE=getter_and_setter

cd ..
java -jar lib.gen_code.jar templates/%TEMPLATE_FILE% params/%PARAM_FILE% | clip