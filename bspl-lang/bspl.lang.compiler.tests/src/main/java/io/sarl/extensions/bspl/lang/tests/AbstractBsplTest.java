/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2025 SARL.io, the Original Authors and Main Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.extensions.bspl.lang.tests;

import static io.sarl.tests.api.tools.TestEObjects.fileGen;
import static io.sarl.tests.api.tools.TestUtils.multilineString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.arakhne.afc.vmutil.FileSystem;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper.Result;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.inject.Inject;
import com.google.inject.Injector;

import io.sarl.extensions.bspl.lang.bspl.BsplProtocolSpecification;
import io.sarl.lang.sarl.SarlScript;
import io.sarl.lang.tests.api.AbstractSarlTest;
import io.sarl.lang.tests.api.ExtendedSARLInjectorProvider;
import io.sarl.lang.tests.api.extensions.JavaVersionCheckExtension;
import io.sarl.tests.api.extensions.ContextInitExtension;
import io.sarl.tests.api.extensions.FieldResetExtension;
import io.sarl.tests.api.extensions.IgnorableTestExtension;
import io.sarl.tests.api.extensions.MockInitializerExtension;
import io.sarl.tests.api.extensions.SarlInjectionExtension;
import io.sarl.tests.api.tools.SarlValidationTestHelper;
import io.sarl.tests.api.tools.TestValidator;
import io.sarl.tests.api.tools.TestValidator.Validator;

/**
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SuppressWarnings("all")
@ExtendWith({
	ContextInitExtension.class, JavaVersionCheckExtension.class, 
	SarlInjectionExtension.class, MockInitializerExtension.class, 
	IgnorableTestExtension.class, FieldResetExtension.class})
@InjectWith(ExtendedBSPLInjectorProvider.class)
public abstract class AbstractBsplTest {

	@Inject
	private Injector injector;

	@Inject
	private ParseHelper<BsplProtocolSpecification> specificationParser;

	@Inject
	private ExtendedCompilationTestHelper compiler;

	@Inject
	private SarlValidationTestHelper validationHelper;

	@Override
	protected void finalize() throws Throwable {
		this.injector = null;
		this.compiler = null;
		this.specificationParser = null;
		this.validationHelper = null;
	}

	/** Replies the injector.
	 *
	 * @return the injector.
	 * @since 0.10
	 */
	protected Injector getInjector() {
		return this.injector;
	}

	/** Parse the code and replies the SARL BSPL specification. This function does not validate the code.
	 *
	 * @param code the code to parse
	 * @return the specification.
	 * @throws Exception in the case the code cannot be parse properly.
	 */
	protected BsplProtocolSpecification specification(String... code) throws Exception {
		return fileGen(this.specificationParser, multilineString(code));
		
	}

	/** Parse the code and replies the SARL BSPL specification. This function validates the code.
	 *
	 * @param code the code to parse
	 * @return the specification.
	 * @throws Exception in the case the code cannot be parse properly.
	 */
	protected BsplProtocolSpecification specificationValid(String... code) throws Exception {
		return fileGen(this.specificationParser, getValidationHelper(), multilineString(code));
		
	}

	/** Validate the given specification.
	 *
	 * @param specification the specification.
	 * @return the validation result accessor.
	 */
	protected Validator validate(BsplProtocolSpecification specification) {
		final var validationHelper = getValidationHelper();
		final var injector = getInjector();
		final var xtextResource = specification.eResource();
		return TestValidator.validate(validationHelper, injector, xtextResource);
	}

	/** Replies the compile helper.
	 *
	 * @return the compile helper.
	 */
	protected ExtendedCompilationTestHelper getCompileHelper() {
		return this.compiler;
	}

	/** Replies the validation helper.
	 *
	 * @return the validation helper.
	 */
	protected SarlValidationTestHelper getValidationHelper() {
		return this.validationHelper;
	}
	
	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public static class ExtendedCompilationTestHelper extends CompilationTestHelper {

		/** Constructor.
		 */
		public ExtendedCompilationTestHelper() {
			//
		}
		
		/**
		 * Asserts that the expected code in the given file is generated for the given source that is assumed to be with SARL syntax.
		 * 
		 * @param source some valid source code written in the language under test
		 * @param typename the name of the generated type. 
		 * @param expected the expected Java source code.
		 * @return the testing tools dedicated to the target language.
		 * @throws IOException if the resource loading fails
		 * @see #assertCompilesTo(CharSequence, String, CharSequence, boolean)
		 */
		public TargetLanguageCompilationTestHelper assertCompilesTo(CharSequence source, String typename, CharSequence expected) throws IOException {
			return assertCompilesTo(source, typename, expected, false);
		}

		/**
		 * Asserts that the expected code in the given file is generated for the given source.
		 * 
		 * @param source some valid source code written in the language under test
		 * @param typename the name of the generated type. 
		 * @param expected the expected Java source code.
		 * @param isJava indicates if the target code is Java ({@code true}) or SARL ({@code false}).
		 * @return the testing tools dedicated to the target language.
		 * @throws IOException if the resource loading fails 
		 * @see #assertCompilesTo(CharSequence, String, CharSequence)
		 */
		public TargetLanguageCompilationTestHelper assertCompilesTo(CharSequence source, String typename, CharSequence expected, boolean isJava) throws IOException {
			final var called = new AtomicReference<TargetLanguageCompilationTestHelper>();
			compile(source, new IAcceptor<CompilationTestHelper.Result>() {
				@Override
				public void accept(Result r) {
					r.assertNoErrors();
					var generatedCode = r.getGeneratedCode(typename);
					if (generatedCode == null) {
						// Check for generated code that is not Java-based
						final var pattern = Pattern.compile(Pattern.quote(typename.replaceAll(Pattern.quote("."), "/")) + "(\\.[^.]+)?$");
						final var allResources = r.getAllGeneratedResources().entrySet();
						final var genResource = allResources.stream().filter(it -> pattern.matcher(it.getKey()).find()).findFirst();
						if (genResource.isPresent()) {
							final var value = genResource.get().getValue();
							if (value != null) {
								final var res = new StringBuilder();
								var first = true;
								for (final var line : value.toString().split("[\n\r]")) {
									if (first) {
										first = false;
									} else {
										res.append("\n");
									}
									res.append(line.stripTrailing());
								}
								generatedCode = res.toString();
							}
						}
					}
					Assert.assertEquals(expected.toString(), generatedCode);
					if (isJava) {
						called.set(JavaTargetLanguageCompilationTestHelper.build(expected));
					} else {
						called.set(SarlTargetLanguageCompilationTestHelper.build(typename, r.getAllGeneratedResources()));
					}
				}
			});
			final var extraHelper = called.get();
			if (extraHelper != null) {
				return extraHelper;
			}
			Assert.fail("Nothing was generated but the expectation was :\n" + expected);
			// Do not test the generated file expressed in the target language because there are issues.
			return new IddleTargetLanguageCompilationTestHelper();
		}

		/**
		 * Asserts that there is no code generation for the given source.
		 * 
		 * @param source some valid source code written in the language under test
		 * @param typename the name of the generated type. 
		 * @throws IOException if the resource loading fails 
		 */
		public void assertNoCompiles(CharSequence source, String typename) throws IOException {
			final var called = new AtomicBoolean(false);
			compile(source, new IAcceptor<CompilationTestHelper.Result>() {
				@Override
				public void accept(Result r) {
					var generatedCode = r.getGeneratedCode(typename);
					if (generatedCode == null) {
						// Check for generated code that is not Java-based
						final var pattern = Pattern.compile(Pattern.quote(typename.replaceAll(Pattern.quote("."), "/")) + "(\\.[^.]+)?$");
						final var allResources = r.getAllGeneratedResources().entrySet();
						final var genResource = allResources.stream().filter(it -> pattern.matcher(it.getKey()).find()).findFirst();
						if (genResource.isPresent()) {
							final var value = genResource.get().getValue();
							if (value != null) {
								final var res = new StringBuilder();
								var first = true;
								for (final var line : value.toString().split("[\n\r]")) {
									if (first) {
										first = false;
									} else {
										res.append("\n");
									}
									res.append(line.stripTrailing());
								}
								generatedCode = res.toString();
								if (!Strings.isEmpty(generatedCode)) {
									Assert.fail("Unexpected generation of code with :\n" + generatedCode);
								}
							}
						}
					}
					called.set(true);
				}
			});
		}
		

	}

	/** Provides tools for testing the generated code.
	 *
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 1.0
	 */
	public interface TargetLanguageCompilationTestHelper {

		/** Assert that there is no error in the target language code.
		 *
		 * @throws Exception in case an error was encountered in the testing process.
		 */
		void assertNoErrorsInTargetLanguage() throws Exception;
		
	}

	/** Implementation of testing tools dedicated to the SARL programming language.
	 * 
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 1.0
	 */
	private static class SarlTargetLanguageCompilationTestHelper extends AbstractSarlTest implements TargetLanguageCompilationTestHelper {

		private static ExtendedSARLInjectorProvider PROVIDER = new ExtendedSARLInjectorProvider();
		
		private String typeName;

		private Map<String, CharSequence> generatedCode;
		
		/** Constructor.
		 */
		private SarlTargetLanguageCompilationTestHelper() {
			//
		}

		/** Build the helper with the SARL injector and not the BSPL injector.
		 *
		 * @param typeName the name of the type that is generated in SARL and that must be validated.
		 * @return the created testing tools.
		 */
		public static SarlTargetLanguageCompilationTestHelper build(String typeName, Map<String, CharSequence> generatedCode) {
			final var helper = PROVIDER.getInjector().getInstance(SarlTargetLanguageCompilationTestHelper.class);
			helper.typeName = typeName;
			helper.generatedCode = generatedCode;
			return helper;
		}
		
		private static String getCodeFor(EObject object) {
			final var node = NodeModelUtils.getNode(object);
			if (node != null) {
				var text = node.getText();
				if (text != null) {
					text = text.trim();
					//text = text.replaceAll("[\n\r\f]+", " "); //$NON-NLS-1$//$NON-NLS-2$
				}
				return Strings.emptyIfNull(text);
			}
			return null;
		}

		private static void appendLines(StringBuilder buffer, String code) {
			int number = 1;
			for (final var line : code.split("\n")) {
				buffer.append(String.format("%6d. ", Integer.valueOf(number)));
				buffer.append(line).append("\n");
				++number;
			}
		}

		private void compileAll(IAcceptor<Result> acceptor) throws IOException {
			var index = 0;
			final var javaResources = new ArrayList<Pair<String, ? extends CharSequence>>();
			final var sarlResources = new ArrayList<Pair<String, ? extends CharSequence>>();
			for (final var source : this.generatedCode.entrySet()) {
				if (FileSystem.hasExtension(source.getKey(), ".sarl")) {
					final var basename = FileSystem.shortBasename(source.getKey());
					final var fileName = new StringBuilder();
					fileName.append(basename).append(index).append(".sarl");
					sarlResources.add(new Pair<>(fileName.toString(), source.getValue()));
					++index;
				} else if (FileSystem.hasExtension(source.getKey(), ".java")) {
					final var basename = FileSystem.shortBasename(source.getKey());
					final var fileName = new StringBuilder();
					fileName.append(basename).append(".java");
					javaResources.add(new Pair<>(fileName.toString(), source.getValue()));
					++index;
				}
			}
			
			final var compiler = getCompileHelper();

			final var resourceSet = compiler.resourceSet(sarlResources.toArray(it -> new Pair[it]));
			
			compiler.compile(resourceSet, acceptor);
		}

		private static String makeId(String packageName, String name) {
			final var buffer = new StringBuilder();
			if (!Strings.isEmpty(packageName)) {
				buffer.append(packageName).append(";");
			}
			buffer.append(name);
			return buffer.toString();
		}

		@Override
		public void assertNoErrorsInTargetLanguage() throws Exception {
			final var ids = new TreeSet<String>();
			compileAll(it -> {
				var errors = it.getErrorsAndWarnings().stream()
						.filter(it0 -> it0.isSyntaxError() || it0.getSeverity() == Severity.ERROR)
						.map(it0 -> {
							final var buffer = new StringBuilder();
							var resource = it.getResourceSet().getResource(it0.getUriToProblem(), true);
							var foundSarlConstruct = false;
							if (resource != null) {
								for (final var obj : resource.getContents()) {
									if (obj instanceof SarlScript script) {
										final var element = script.getXtendTypes().stream().filter(it1 -> !Strings.isEmpty(it1.getName())).findFirst();
										if (element.isPresent()) {
											final var id = makeId(script.getPackage(), element.get().getName());
											if (this.typeName.equals(id)) {
												if (ids.add(id)) {
													buffer.append("---------- ").append(id).append("\n");
													final var code = Strings.emptyIfNull(getCodeFor(obj));
													if (!Strings.isEmpty(code)) {
														appendLines(buffer, code);
														buffer.append("\n");
													}
													buffer.append("\n");
												}
												foundSarlConstruct = true;
											}
										}
									}
								}
							}
							if (foundSarlConstruct) {
								buffer.append(it0.toString().replaceAll("file:.* line ", "line "));
							}
							return buffer.toString();
						})
						.filter(it0 -> !it0.isEmpty())
						.collect(Collectors.joining("\n"));
				assertEquals("", errors, "The generated code contains errors.");
			});
		}

	}

	/** Implementation of testing tools dedicated to the Java programming language.
	 * 
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 1.0
	 */
	private static class JavaTargetLanguageCompilationTestHelper extends AbstractSarlTest implements TargetLanguageCompilationTestHelper {

		private CharSequence expectedTargetCode;
		
		/** Constructor.
		 */
		private JavaTargetLanguageCompilationTestHelper() {
		}

		/** Build the helper with the SARL injector and not the BSPL injector.
		 *
		 * @param expected the input code in the target language.
		 * @return the created testing tools.
		 */
		public static JavaTargetLanguageCompilationTestHelper build(CharSequence expected) {
			final var helper = new JavaTargetLanguageCompilationTestHelper();
			helper.expectedTargetCode = expected;
			return helper;
		}

		@Override
		public void assertNoErrorsInTargetLanguage() throws Exception {
			//TODO: fail("not yet implemented");
		}

	}

	/**
	 * @author $Author: sgalland$
	 * @version $Name$ $Revision$ $Date$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 1.0
	 */
	private static class IddleTargetLanguageCompilationTestHelper implements TargetLanguageCompilationTestHelper {

		@Override
		public void assertNoErrorsInTargetLanguage() throws Exception {
			//
		}

	}

}
