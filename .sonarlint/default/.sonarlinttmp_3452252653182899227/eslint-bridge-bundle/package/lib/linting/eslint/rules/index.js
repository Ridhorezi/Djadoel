"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.rules = void 0;
const anchor_precedence_1 = require("./anchor-precedence");
const argument_type_1 = require("./argument-type");
const arguments_order_1 = require("./arguments-order");
const arguments_usage_1 = require("./arguments-usage");
const array_callback_without_return_1 = require("./array-callback-without-return");
const array_constructor_1 = require("./array-constructor");
const arrow_function_convention_1 = require("./arrow-function-convention");
const assertions_in_tests_1 = require("./assertions-in-tests");
const aws_apigateway_public_api_1 = require("./aws-apigateway-public-api");
const aws_ec2_rds_dms_public_1 = require("./aws-ec2-rds-dms-public");
const aws_ec2_unencrypted_ebs_volume_1 = require("./aws-ec2-unencrypted-ebs-volume");
const aws_efs_unencrypted_1 = require("./aws-efs-unencrypted");
const aws_iam_all_privileges_1 = require("./aws-iam-all-privileges");
const aws_iam_all_resources_accessible_1 = require("./aws-iam-all-resources-accessible");
const aws_iam_privilege_escalation_1 = require("./aws-iam-privilege-escalation");
const aws_iam_public_access_1 = require("./aws-iam-public-access");
const aws_opensearchservice_domain_1 = require("./aws-opensearchservice-domain");
const aws_rds_unencrypted_databases_1 = require("./aws-rds-unencrypted-databases");
const aws_restricted_ip_admin_access_1 = require("./aws-restricted-ip-admin-access");
const aws_s3_bucket_granted_access_1 = require("./aws-s3-bucket-granted-access");
const aws_s3_bucket_insecure_http_1 = require("./aws-s3-bucket-insecure-http");
const aws_s3_bucket_public_access_1 = require("./aws-s3-bucket-public-access");
const aws_s3_bucket_server_encryption_1 = require("./aws-s3-bucket-server-encryption");
const aws_s3_bucket_versioning_1 = require("./aws-s3-bucket-versioning");
const aws_sagemaker_unencrypted_notebook_1 = require("./aws-sagemaker-unencrypted-notebook");
const aws_sns_unencrypted_topics_1 = require("./aws-sns-unencrypted-topics");
const aws_sqs_unencrypted_queue_1 = require("./aws-sqs-unencrypted-queue");
const bitwise_operators_1 = require("./bitwise-operators");
const bool_param_default_1 = require("./bool-param-default");
const call_argument_line_1 = require("./call-argument-line");
const certificate_transparency_1 = require("./certificate-transparency");
const chai_determinate_assertion_1 = require("./chai-determinate-assertion");
const class_name_1 = require("./class-name");
const class_prototype_1 = require("./class-prototype");
const code_eval_1 = require("./code-eval");
const comma_or_logical_or_case_1 = require("./comma-or-logical-or-case");
const comment_regex_1 = require("./comment-regex");
const concise_regex_1 = require("./concise-regex");
const conditional_indentation_1 = require("./conditional-indentation");
const confidential_information_logging_1 = require("./confidential-information-logging");
const constructor_for_side_effects_1 = require("./constructor-for-side-effects");
const content_length_1 = require("./content-length");
const content_security_policy_1 = require("./content-security-policy");
const cookie_no_httponly_1 = require("./cookie-no-httponly");
const cookies_1 = require("./cookies");
const cors_1 = require("./cors");
const csrf_1 = require("./csrf");
const cyclomatic_complexity_1 = require("./cyclomatic-complexity");
const declarations_in_global_scope_1 = require("./declarations-in-global-scope");
const deprecation_1 = require("./deprecation");
const destructuring_assignment_syntax_1 = require("./destructuring-assignment-syntax");
const different_types_comparison_1 = require("./different-types-comparison");
const disabled_auto_escaping_1 = require("./disabled-auto-escaping");
const disabled_resource_integrity_1 = require("./disabled-resource-integrity");
const disabled_timeout_1 = require("./disabled-timeout");
const dns_prefetching_1 = require("./dns-prefetching");
const duplicates_in_character_class_1 = require("./duplicates-in-character-class");
const empty_string_repetition_1 = require("./empty-string-repetition");
const encryption_1 = require("./encryption");
const encryption_secure_mode_1 = require("./encryption-secure-mode");
const existing_groups_1 = require("./existing-groups");
const expression_complexity_1 = require("./expression-complexity");
const file_header_1 = require("./file-header");
const file_name_differ_from_class_1 = require("./file-name-differ-from-class");
const file_permissions_1 = require("./file-permissions");
const file_uploads_1 = require("./file-uploads");
const fixme_tag_1 = require("./fixme-tag");
const for_in_1 = require("./for-in");
const for_loop_increment_sign_1 = require("./for-loop-increment-sign");
const frame_ancestors_1 = require("./frame-ancestors");
const function_inside_loop_1 = require("./function-inside-loop");
const function_name_1 = require("./function-name");
const function_return_type_1 = require("./function-return-type");
const future_reserved_words_1 = require("./future-reserved-words");
const generator_without_yield_1 = require("./generator-without-yield");
const hashing_1 = require("./hashing");
const hidden_files_1 = require("./hidden-files");
const in_operator_type_error_1 = require("./in-operator-type-error");
const inconsistent_function_call_1 = require("./inconsistent-function-call");
const index_of_compare_to_positive_number_1 = require("./index-of-compare-to-positive-number");
const insecure_cookie_1 = require("./insecure-cookie");
const insecure_jwt_token_1 = require("./insecure-jwt-token");
const inverted_assertion_arguments_1 = require("./inverted-assertion-arguments");
const label_position_1 = require("./label-position");
const link_with_target_blank_1 = require("./link-with-target-blank");
const max_union_size_1 = require("./max-union-size");
const misplaced_loop_counter_1 = require("./misplaced-loop-counter");
const nested_control_flow_1 = require("./nested-control-flow");
const new_operator_misuse_1 = require("./new-operator-misuse");
const no_accessor_field_mismatch_1 = require("./no-accessor-field-mismatch");
const no_alphabetical_sort_1 = require("./no-alphabetical-sort");
const no_angular_bypass_sanitization_1 = require("./no-angular-bypass-sanitization");
const no_array_delete_1 = require("./no-array-delete");
const no_associative_arrays_1 = require("./no-associative-arrays");
const no_built_in_override_1 = require("./no-built-in-override");
const no_case_label_in_switch_1 = require("./no-case-label-in-switch");
const no_clear_text_protocols_1 = require("./no-clear-text-protocols");
const no_code_after_done_1 = require("./no-code-after-done");
const no_commented_code_1 = require("./no-commented-code");
const no_dead_store_1 = require("./no-dead-store");
const no_delete_var_1 = require("./no-delete-var");
const no_duplicate_in_composite_1 = require("./no-duplicate-in-composite");
const no_empty_after_reluctant_1 = require("./no-empty-after-reluctant");
const no_empty_alternatives_1 = require("./no-empty-alternatives");
const no_empty_group_1 = require("./no-empty-group");
const no_equals_in_for_termination_1 = require("./no-equals-in-for-termination");
const no_exclusive_tests_1 = require("./no-exclusive-tests");
const no_for_in_iterable_1 = require("./no-for-in-iterable");
const no_function_declaration_in_block_1 = require("./no-function-declaration-in-block");
const no_global_this_1 = require("./no-global-this");
const no_globals_shadowing_1 = require("./no-globals-shadowing");
const no_hardcoded_credentials_1 = require("./no-hardcoded-credentials");
const no_hardcoded_ip_1 = require("./no-hardcoded-ip");
const no_hook_setter_in_body_1 = require("./no-hook-setter-in-body");
const no_ignored_exceptions_1 = require("./no-ignored-exceptions");
const no_implicit_dependencies_1 = require("./no-implicit-dependencies");
const no_implicit_global_1 = require("./no-implicit-global");
const no_in_misuse_1 = require("./no-in-misuse");
const no_incomplete_assertions_1 = require("./no-incomplete-assertions");
const no_inconsistent_returns_1 = require("./no-inconsistent-returns");
const no_incorrect_string_concat_1 = require("./no-incorrect-string-concat");
const no_infinite_loop_1 = require("./no-infinite-loop");
const no_intrusive_permissions_1 = require("./no-intrusive-permissions");
const no_invalid_await_1 = require("./no-invalid-await");
const no_invariant_returns_1 = require("./no-invariant-returns");
const no_ip_forward_1 = require("./no-ip-forward");
const no_labels_1 = require("./no-labels");
const no_mime_sniff_1 = require("./no-mime-sniff");
const no_misleading_array_reverse_1 = require("./no-misleading-array-reverse");
const no_misused_promises_1 = require("./no-misused-promises");
const no_mixed_content_1 = require("./no-mixed-content");
const no_nested_assignment_1 = require("./no-nested-assignment");
const no_nested_conditional_1 = require("./no-nested-conditional");
const no_nested_incdec_1 = require("./no-nested-incdec");
const no_os_command_from_path_1 = require("./no-os-command-from-path");
const no_parameter_reassignment_1 = require("./no-parameter-reassignment");
const no_primitive_wrappers_1 = require("./no-primitive-wrappers");
const no_redundant_assignments_1 = require("./no-redundant-assignments");
const no_redundant_optional_1 = require("./no-redundant-optional");
const no_redundant_parentheses_1 = require("./no-redundant-parentheses");
const no_reference_error_1 = require("./no-reference-error");
const no_referrer_policy_1 = require("./no-referrer-policy");
const no_require_or_define_1 = require("./no-require-or-define");
const no_return_type_any_1 = require("./no-return-type-any");
const no_same_argument_assert_1 = require("./no-same-argument-assert");
const no_tab_1 = require("./no-tab");
const no_try_promise_1 = require("./no-try-promise");
const no_undefined_argument_1 = require("./no-undefined-argument");
const no_undefined_assignment_1 = require("./no-undefined-assignment");
const no_unenclosed_multiline_block_1 = require("./no-unenclosed-multiline-block");
const no_uniq_key_1 = require("./no-uniq-key");
const no_unsafe_unzip_1 = require("./no-unsafe-unzip");
const no_unthrown_error_1 = require("./no-unthrown-error");
const no_unused_function_argument_1 = require("./no-unused-function-argument");
const no_useless_increment_1 = require("./no-useless-increment");
const no_useless_intersection_1 = require("./no-useless-intersection");
const no_useless_react_setstate_1 = require("./no-useless-react-setstate");
const no_variable_usage_before_declaration_1 = require("./no-variable-usage-before-declaration");
const no_vue_bypass_sanitization_1 = require("./no-vue-bypass-sanitization");
const no_weak_cipher_1 = require("./no-weak-cipher");
const no_weak_keys_1 = require("./no-weak-keys");
const no_wildcard_import_1 = require("./no-wildcard-import");
const non_number_in_arithmetic_expression_1 = require("./non-number-in-arithmetic-expression");
const null_dereference_1 = require("./null-dereference");
const operation_returning_nan_1 = require("./operation-returning-nan");
const os_command_1 = require("./os-command");
const post_message_1 = require("./post-message");
const prefer_default_last_1 = require("./prefer-default-last");
const prefer_promise_shorthand_1 = require("./prefer-promise-shorthand");
const prefer_type_guard_1 = require("./prefer-type-guard");
const process_argv_1 = require("./process-argv");
const production_debug_1 = require("./production-debug");
const pseudo_random_1 = require("./pseudo-random");
const publicly_writable_directories_1 = require("./publicly-writable-directories");
const redundant_type_aliases_1 = require("./redundant-type-aliases");
const regex_complexity_1 = require("./regex-complexity");
const regular_expr_1 = require("./regular-expr");
const rules_of_hooks_1 = require("./rules-of-hooks");
const session_regeneration_1 = require("./session-regeneration");
const shorthand_property_grouping_1 = require("./shorthand-property-grouping");
const single_char_in_character_classes_1 = require("./single-char-in-character-classes");
const single_character_alternation_1 = require("./single-character-alternation");
const slow_regex_1 = require("./slow-regex");
const sockets_1 = require("./sockets");
const sonar_block_scoped_var_1 = require("./sonar-block-scoped-var");
const sonar_jsx_no_leaked_render_1 = require("./sonar-jsx-no-leaked-render");
const sonar_max_lines_1 = require("./sonar-max-lines");
const sonar_max_lines_per_function_1 = require("./sonar-max-lines-per-function");
const sonar_max_params_1 = require("./sonar-max-params");
const sonar_no_control_regex_1 = require("./sonar-no-control-regex");
const sonar_no_dupe_keys_1 = require("./sonar-no-dupe-keys");
const sonar_no_empty_character_class_1 = require("./sonar-no-empty-character-class");
const sonar_no_fallthrough_1 = require("./sonar-no-fallthrough");
const sonar_no_invalid_regexp_1 = require("./sonar-no-invalid-regexp");
const sonar_no_misleading_character_class_1 = require("./sonar-no-misleading-character-class");
const sonar_no_regex_spaces_1 = require("./sonar-no-regex-spaces");
const sonar_no_unused_class_component_methods_1 = require("./sonar-no-unused-class-component-methods");
const sonar_no_unused_vars_1 = require("./sonar-no-unused-vars");
const sonar_prefer_optional_chain_1 = require("./sonar-prefer-optional-chain");
const sonar_prefer_regexp_exec_1 = require("./sonar-prefer-regexp-exec");
const sql_queries_1 = require("./sql-queries");
const standard_input_1 = require("./standard-input");
const stateful_regex_1 = require("./stateful-regex");
const strict_transport_security_1 = require("./strict-transport-security");
const strings_comparison_1 = require("./strings-comparison");
const super_invocation_1 = require("./super-invocation");
const switch_without_default_1 = require("./switch-without-default");
const test_check_exception_1 = require("./test-check-exception");
const todo_tag_1 = require("./todo-tag");
const too_many_break_or_continue_in_loop_1 = require("./too-many-break-or-continue-in-loop");
const unicode_aware_regex_1 = require("./unicode-aware-regex");
const unnecessary_character_escapes_1 = require("./unnecessary-character-escapes");
const unused_import_1 = require("./unused-import");
const unused_named_groups_1 = require("./unused-named-groups");
const unverified_certificate_1 = require("./unverified-certificate");
const unverified_hostname_1 = require("./unverified-hostname");
const updated_const_var_1 = require("./updated-const-var");
const updated_loop_counter_1 = require("./updated-loop-counter");
const use_type_alias_1 = require("./use-type-alias");
const useless_string_operation_1 = require("./useless-string-operation");
const values_not_convertible_to_numbers_1 = require("./values-not-convertible-to-numbers");
const variable_name_1 = require("./variable-name");
const void_use_1 = require("./void-use");
const weak_ssl_1 = require("./weak-ssl");
const web_sql_database_1 = require("./web-sql-database");
const x_powered_by_1 = require("./x-powered-by");
const xml_parser_xxe_1 = require("./xml-parser-xxe");
const xpath_1 = require("./xpath");
/**
 * The set of internal ESLint-based rules
 */
const rules = {};
exports.rules = rules;
/**
 * Maps ESLint rule keys declared in the JavaScript checks to rule implementations
 */
rules['anchor-precedence'] = anchor_precedence_1.rule;
rules['argument-type'] = argument_type_1.rule;
rules['arguments-order'] = arguments_order_1.rule;
rules['arguments-usage'] = arguments_usage_1.rule;
rules['array-callback-without-return'] = array_callback_without_return_1.rule;
rules['array-constructor'] = array_constructor_1.rule;
rules['arrow-function-convention'] = arrow_function_convention_1.rule;
rules['assertions-in-tests'] = assertions_in_tests_1.rule;
rules['aws-apigateway-public-api'] = aws_apigateway_public_api_1.rule;
rules['aws-ec2-rds-dms-public'] = aws_ec2_rds_dms_public_1.rule;
rules['aws-ec2-unencrypted-ebs-volume'] = aws_ec2_unencrypted_ebs_volume_1.rule;
rules['aws-efs-unencrypted'] = aws_efs_unencrypted_1.rule;
rules['aws-iam-all-privileges'] = aws_iam_all_privileges_1.rule;
rules['aws-iam-all-resources-accessible'] = aws_iam_all_resources_accessible_1.rule;
rules['aws-iam-privilege-escalation'] = aws_iam_privilege_escalation_1.rule;
rules['aws-iam-public-access'] = aws_iam_public_access_1.rule;
rules['aws-opensearchservice-domain'] = aws_opensearchservice_domain_1.rule;
rules['aws-rds-unencrypted-databases'] = aws_rds_unencrypted_databases_1.rule;
rules['aws-restricted-ip-admin-access'] = aws_restricted_ip_admin_access_1.rule;
rules['aws-s3-bucket-granted-access'] = aws_s3_bucket_granted_access_1.rule;
rules['aws-s3-bucket-insecure-http'] = aws_s3_bucket_insecure_http_1.rule;
rules['aws-s3-bucket-public-access'] = aws_s3_bucket_public_access_1.rule;
rules['aws-s3-bucket-server-encryption'] = aws_s3_bucket_server_encryption_1.rule;
rules['aws-s3-bucket-versioning'] = aws_s3_bucket_versioning_1.rule;
rules['aws-sagemaker-unencrypted-notebook'] = aws_sagemaker_unencrypted_notebook_1.rule;
rules['aws-sns-unencrypted-topics'] = aws_sns_unencrypted_topics_1.rule;
rules['aws-sqs-unencrypted-queue'] = aws_sqs_unencrypted_queue_1.rule;
rules['bitwise-operators'] = bitwise_operators_1.rule;
rules['bool-param-default'] = bool_param_default_1.rule;
rules['call-argument-line'] = call_argument_line_1.rule;
rules['certificate-transparency'] = certificate_transparency_1.rule;
rules['chai-determinate-assertion'] = chai_determinate_assertion_1.rule;
rules['class-name'] = class_name_1.rule;
rules['class-prototype'] = class_prototype_1.rule;
rules['code-eval'] = code_eval_1.rule;
rules['comma-or-logical-or-case'] = comma_or_logical_or_case_1.rule;
rules['comment-regex'] = comment_regex_1.rule;
rules['concise-regex'] = concise_regex_1.rule;
rules['conditional-indentation'] = conditional_indentation_1.rule;
rules['confidential-information-logging'] = confidential_information_logging_1.rule;
rules['constructor-for-side-effects'] = constructor_for_side_effects_1.rule;
rules['content-length'] = content_length_1.rule;
rules['content-security-policy'] = content_security_policy_1.rule;
rules['cookie-no-httponly'] = cookie_no_httponly_1.rule;
rules['cookies'] = cookies_1.rule;
rules['cors'] = cors_1.rule;
rules['csrf'] = csrf_1.rule;
rules['cyclomatic-complexity'] = cyclomatic_complexity_1.rule;
rules['declarations-in-global-scope'] = declarations_in_global_scope_1.rule;
rules['deprecation'] = deprecation_1.rule;
rules['destructuring-assignment-syntax'] = destructuring_assignment_syntax_1.rule;
rules['different-types-comparison'] = different_types_comparison_1.rule;
rules['disabled-auto-escaping'] = disabled_auto_escaping_1.rule;
rules['disabled-resource-integrity'] = disabled_resource_integrity_1.rule;
rules['disabled-timeout'] = disabled_timeout_1.rule;
rules['dns-prefetching'] = dns_prefetching_1.rule;
rules['duplicates-in-character-class'] = duplicates_in_character_class_1.rule;
rules['empty-string-repetition'] = empty_string_repetition_1.rule;
rules['encryption'] = encryption_1.rule;
rules['encryption-secure-mode'] = encryption_secure_mode_1.rule;
rules['existing-groups'] = existing_groups_1.rule;
rules['expression-complexity'] = expression_complexity_1.rule;
rules['file-header'] = file_header_1.rule;
rules['file-name-differ-from-class'] = file_name_differ_from_class_1.rule;
rules['file-permissions'] = file_permissions_1.rule;
rules['file-uploads'] = file_uploads_1.rule;
rules['fixme-tag'] = fixme_tag_1.rule;
rules['for-in'] = for_in_1.rule;
rules['for-loop-increment-sign'] = for_loop_increment_sign_1.rule;
rules['frame-ancestors'] = frame_ancestors_1.rule;
rules['function-inside-loop'] = function_inside_loop_1.rule;
rules['function-name'] = function_name_1.rule;
rules['function-return-type'] = function_return_type_1.rule;
rules['future-reserved-words'] = future_reserved_words_1.rule;
rules['generator-without-yield'] = generator_without_yield_1.rule;
rules['hashing'] = hashing_1.rule;
rules['hidden-files'] = hidden_files_1.rule;
rules['in-operator-type-error'] = in_operator_type_error_1.rule;
rules['inconsistent-function-call'] = inconsistent_function_call_1.rule;
rules['index-of-compare-to-positive-number'] = index_of_compare_to_positive_number_1.rule;
rules['insecure-cookie'] = insecure_cookie_1.rule;
rules['insecure-jwt-token'] = insecure_jwt_token_1.rule;
rules['inverted-assertion-arguments'] = inverted_assertion_arguments_1.rule;
rules['label-position'] = label_position_1.rule;
rules['link-with-target-blank'] = link_with_target_blank_1.rule;
rules['max-union-size'] = max_union_size_1.rule;
rules['misplaced-loop-counter'] = misplaced_loop_counter_1.rule;
rules['nested-control-flow'] = nested_control_flow_1.rule;
rules['new-operator-misuse'] = new_operator_misuse_1.rule;
rules['no-accessor-field-mismatch'] = no_accessor_field_mismatch_1.rule;
rules['no-alphabetical-sort'] = no_alphabetical_sort_1.rule;
rules['no-angular-bypass-sanitization'] = no_angular_bypass_sanitization_1.rule;
rules['no-array-delete'] = no_array_delete_1.rule;
rules['no-associative-arrays'] = no_associative_arrays_1.rule;
rules['no-built-in-override'] = no_built_in_override_1.rule;
rules['no-case-label-in-switch'] = no_case_label_in_switch_1.rule;
rules['no-clear-text-protocols'] = no_clear_text_protocols_1.rule;
rules['no-code-after-done'] = no_code_after_done_1.rule;
rules['no-commented-code'] = no_commented_code_1.rule;
rules['no-dead-store'] = no_dead_store_1.rule;
rules['no-delete-var'] = no_delete_var_1.rule;
rules['no-duplicate-in-composite'] = no_duplicate_in_composite_1.rule;
rules['no-empty-after-reluctant'] = no_empty_after_reluctant_1.rule;
rules['no-empty-alternatives'] = no_empty_alternatives_1.rule;
rules['no-empty-group'] = no_empty_group_1.rule;
rules['no-equals-in-for-termination'] = no_equals_in_for_termination_1.rule;
rules['no-exclusive-tests'] = no_exclusive_tests_1.rule;
rules['no-for-in-iterable'] = no_for_in_iterable_1.rule;
rules['no-function-declaration-in-block'] = no_function_declaration_in_block_1.rule;
rules['no-global-this'] = no_global_this_1.rule;
rules['no-globals-shadowing'] = no_globals_shadowing_1.rule;
rules['no-hardcoded-credentials'] = no_hardcoded_credentials_1.rule;
rules['no-hardcoded-ip'] = no_hardcoded_ip_1.rule;
rules['no-hook-setter-in-body'] = no_hook_setter_in_body_1.rule;
rules['no-ignored-exceptions'] = no_ignored_exceptions_1.rule;
rules['no-implicit-dependencies'] = no_implicit_dependencies_1.rule;
rules['no-implicit-global'] = no_implicit_global_1.rule;
rules['no-in-misuse'] = no_in_misuse_1.rule;
rules['no-incomplete-assertions'] = no_incomplete_assertions_1.rule;
rules['no-inconsistent-returns'] = no_inconsistent_returns_1.rule;
rules['no-incorrect-string-concat'] = no_incorrect_string_concat_1.rule;
rules['no-infinite-loop'] = no_infinite_loop_1.rule;
rules['no-intrusive-permissions'] = no_intrusive_permissions_1.rule;
rules['no-invalid-await'] = no_invalid_await_1.rule;
rules['no-invariant-returns'] = no_invariant_returns_1.rule;
rules['no-ip-forward'] = no_ip_forward_1.rule;
rules['no-labels'] = no_labels_1.rule;
rules['no-mime-sniff'] = no_mime_sniff_1.rule;
rules['no-misleading-array-reverse'] = no_misleading_array_reverse_1.rule;
rules['no-misused-promises'] = no_misused_promises_1.rule;
rules['no-mixed-content'] = no_mixed_content_1.rule;
rules['no-nested-assignment'] = no_nested_assignment_1.rule;
rules['no-nested-conditional'] = no_nested_conditional_1.rule;
rules['no-nested-incdec'] = no_nested_incdec_1.rule;
rules['no-os-command-from-path'] = no_os_command_from_path_1.rule;
rules['no-parameter-reassignment'] = no_parameter_reassignment_1.rule;
rules['no-primitive-wrappers'] = no_primitive_wrappers_1.rule;
rules['no-redundant-assignments'] = no_redundant_assignments_1.rule;
rules['no-redundant-optional'] = no_redundant_optional_1.rule;
rules['no-redundant-parentheses'] = no_redundant_parentheses_1.rule;
rules['no-reference-error'] = no_reference_error_1.rule;
rules['no-referrer-policy'] = no_referrer_policy_1.rule;
rules['no-require-or-define'] = no_require_or_define_1.rule;
rules['no-return-type-any'] = no_return_type_any_1.rule;
rules['no-same-argument-assert'] = no_same_argument_assert_1.rule;
rules['no-tab'] = no_tab_1.rule;
rules['no-try-promise'] = no_try_promise_1.rule;
rules['no-undefined-argument'] = no_undefined_argument_1.rule;
rules['no-undefined-assignment'] = no_undefined_assignment_1.rule;
rules['no-unenclosed-multiline-block'] = no_unenclosed_multiline_block_1.rule;
rules['no-uniq-key'] = no_uniq_key_1.rule;
rules['no-unsafe-unzip'] = no_unsafe_unzip_1.rule;
rules['no-unthrown-error'] = no_unthrown_error_1.rule;
rules['no-unused-function-argument'] = no_unused_function_argument_1.rule;
rules['no-useless-increment'] = no_useless_increment_1.rule;
rules['no-useless-intersection'] = no_useless_intersection_1.rule;
rules['no-useless-react-setstate'] = no_useless_react_setstate_1.rule;
rules['no-variable-usage-before-declaration'] = no_variable_usage_before_declaration_1.rule;
rules['no-vue-bypass-sanitization'] = no_vue_bypass_sanitization_1.rule;
rules['no-weak-cipher'] = no_weak_cipher_1.rule;
rules['no-weak-keys'] = no_weak_keys_1.rule;
rules['no-wildcard-import'] = no_wildcard_import_1.rule;
rules['non-number-in-arithmetic-expression'] = non_number_in_arithmetic_expression_1.rule;
rules['null-dereference'] = null_dereference_1.rule;
rules['operation-returning-nan'] = operation_returning_nan_1.rule;
rules['os-command'] = os_command_1.rule;
rules['post-message'] = post_message_1.rule;
rules['prefer-default-last'] = prefer_default_last_1.rule;
rules['prefer-promise-shorthand'] = prefer_promise_shorthand_1.rule;
rules['prefer-type-guard'] = prefer_type_guard_1.rule;
rules['process-argv'] = process_argv_1.rule;
rules['production-debug'] = production_debug_1.rule;
rules['pseudo-random'] = pseudo_random_1.rule;
rules['publicly-writable-directories'] = publicly_writable_directories_1.rule;
rules['redundant-type-aliases'] = redundant_type_aliases_1.rule;
rules['regex-complexity'] = regex_complexity_1.rule;
rules['regular-expr'] = regular_expr_1.rule;
rules['rules-of-hooks'] = rules_of_hooks_1.rule;
rules['session-regeneration'] = session_regeneration_1.rule;
rules['shorthand-property-grouping'] = shorthand_property_grouping_1.rule;
rules['single-char-in-character-classes'] = single_char_in_character_classes_1.rule;
rules['single-character-alternation'] = single_character_alternation_1.rule;
rules['slow-regex'] = slow_regex_1.rule;
rules['sockets'] = sockets_1.rule;
rules['sonar-block-scoped-var'] = sonar_block_scoped_var_1.rule;
rules['sonar-jsx-no-leaked-render'] = sonar_jsx_no_leaked_render_1.rule;
rules['sonar-max-lines'] = sonar_max_lines_1.rule;
rules['sonar-max-lines-per-function'] = sonar_max_lines_per_function_1.rule;
rules['sonar-max-params'] = sonar_max_params_1.rule;
rules['sonar-no-control-regex'] = sonar_no_control_regex_1.rule;
rules['sonar-no-dupe-keys'] = sonar_no_dupe_keys_1.rule;
rules['sonar-no-empty-character-class'] = sonar_no_empty_character_class_1.rule;
rules['sonar-no-fallthrough'] = sonar_no_fallthrough_1.rule;
rules['sonar-no-invalid-regexp'] = sonar_no_invalid_regexp_1.rule;
rules['sonar-no-misleading-character-class'] = sonar_no_misleading_character_class_1.rule;
rules['sonar-no-regex-spaces'] = sonar_no_regex_spaces_1.rule;
rules['sonar-no-unused-class-component-methods'] = sonar_no_unused_class_component_methods_1.rule;
rules['sonar-no-unused-vars'] = sonar_no_unused_vars_1.rule;
rules['sonar-prefer-optional-chain'] = sonar_prefer_optional_chain_1.rule;
rules['sonar-prefer-regexp-exec'] = sonar_prefer_regexp_exec_1.rule;
rules['sql-queries'] = sql_queries_1.rule;
rules['standard-input'] = standard_input_1.rule;
rules['stateful-regex'] = stateful_regex_1.rule;
rules['strict-transport-security'] = strict_transport_security_1.rule;
rules['strings-comparison'] = strings_comparison_1.rule;
rules['super-invocation'] = super_invocation_1.rule;
rules['switch-without-default'] = switch_without_default_1.rule;
rules['test-check-exception'] = test_check_exception_1.rule;
rules['todo-tag'] = todo_tag_1.rule;
rules['too-many-break-or-continue-in-loop'] = too_many_break_or_continue_in_loop_1.rule;
rules['unicode-aware-regex'] = unicode_aware_regex_1.rule;
rules['unnecessary-character-escapes'] = unnecessary_character_escapes_1.rule;
rules['unused-import'] = unused_import_1.rule;
rules['unused-named-groups'] = unused_named_groups_1.rule;
rules['unverified-certificate'] = unverified_certificate_1.rule;
rules['unverified-hostname'] = unverified_hostname_1.rule;
rules['updated-const-var'] = updated_const_var_1.rule;
rules['updated-loop-counter'] = updated_loop_counter_1.rule;
rules['use-type-alias'] = use_type_alias_1.rule;
rules['useless-string-operation'] = useless_string_operation_1.rule;
rules['values-not-convertible-to-numbers'] = values_not_convertible_to_numbers_1.rule;
rules['variable-name'] = variable_name_1.rule;
rules['void-use'] = void_use_1.rule;
rules['weak-ssl'] = weak_ssl_1.rule;
rules['web-sql-database'] = web_sql_database_1.rule;
rules['x-powered-by'] = x_powered_by_1.rule;
rules['xml-parser-xxe'] = xml_parser_xxe_1.rule;
rules['xpath'] = xpath_1.rule;
//# sourceMappingURL=index.js.map