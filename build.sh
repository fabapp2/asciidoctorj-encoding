# fails with
#
# org.asciidoctor.jruby.internal.AsciidoctorCoreException: org.jruby.exceptions.EncodingError$CompatibilityError: (CompatibilityError) incompatible character encodings: UTF-8 and US-ASCII
#        at AsciidoctorJEncodingTest.test_renameMe(AsciidoctorJEncodingTest.java:29)
# Caused by: org.jruby.exceptions.EncodingError$CompatibilityError: (CompatibilityError) incompatible character encodings: UTF-8 and US-ASCII
export JAVA_TOOL_OPTIONS=-Dfile.encoding="US-ASCII"
mvn clean install

# Setting the encoding fixes the error
export JAVA_TOOL_OPTIONS=-Dfile.encoding="UTF-8"

mvn clean install

# reset encoding
export JAVA_TOOL_OPTIONS=-Dfile.encoding="US-ASCII"