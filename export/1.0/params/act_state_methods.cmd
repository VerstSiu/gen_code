SET TEMPLATE_FILE=act_state_methods
SET PARAM_FILE=act_state_methods

cd ..
java -jar lib.gen_code.jar templates/%TEMPLATE_FILE% params/%PARAM_FILE% | clip