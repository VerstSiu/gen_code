SET TEMPLATE_FILE=interface_keys
SET PARAM_FILE=interface_keys

cd ..
java -jar lib.gen_code.jar templates/%TEMPLATE_FILE% params/%PARAM_FILE% | clip