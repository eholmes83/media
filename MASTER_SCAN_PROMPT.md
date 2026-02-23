# Master Prompt: Scan Project for Changes and Update README

Copy and paste this entire prompt into GitHub Copilot to automatically scan your Spring Boot project for changes and update the README.md file.

---

## 🎯 MASTER PROMPT - Use This One First

```
You are a technical documentation expert. Analyze my Spring Boot project and update the README.md file with all recent changes.

PROJECT CONTEXT:
- Spring Boot 4.0.2 application with Java 21
- H2 in-memory database
- JPA/Hibernate ORM with optimistic locking
- REST API with social media entities (SocialUser, SocialGroup, Post, SocialProfile)
- Maven-based project

SCAN INSTRUCTIONS:
1. Examine all files in these directories and identify changes:
   ├── src/main/java/com/echapps/social/media/models/ (Entity changes)
   ├── src/main/java/com/echapps/social/media/controllers/ (REST endpoints)
   ├── src/main/java/com/echapps/social/media/services/ (Business logic)
   ├── src/main/java/com/echapps/social/media/repositories/ (Data access)
   ├── pom.xml (Dependencies)
   └── application.properties (Configuration)

2. For EACH file changed, document:
   - File path and name
   - What changed (specific lines/classes/methods)
   - Why it changed (reason/purpose)
   - Impact on application
   - Breaking changes (if any)
   - Related files affected

3. Categorize changes into:
   a) NEW FEATURES
      - Describe feature
      - Why it was added
      - How to use it
      - Example code
   
   b) BUG FIXES
      - What was broken
      - Root cause
      - How it was fixed
      - Impact on users
   
   c) REFACTORING
      - What was refactored
      - Why
      - Benefits
      - Migration steps (if needed)
   
   d) DEPENDENCY UPDATES
      - Old version → New version
      - Why updated
      - Breaking changes
      - Migration guide
   
   e) CONFIGURATION CHANGES
      - New properties
      - Changed values
      - Documentation

4. UPDATE README.md SECTIONS:

   a) Version Section
      - Increment version number using semantic versioning:
        * MAJOR (X.0.0) for breaking changes
        * MINOR (0.X.0) for new features
        * PATCH (0.0.X) for bug fixes
      - Current version: 0.0.1-SNAPSHOT
   
   b) Recent Changes Section
      - Create new section: "## Recent Changes (Version X.X.X)"
      - List all changes grouped by category
      - Use ✅ for completed items
      - Include dates
   
   c) Modified Files Section
      - List every file that was changed
      - Brief description of what changed in each
      - Format as bullet list with file path
   
   d) Entity Relationships Section
      - Update ASCII diagram if entity structure changed
      - Add/remove entities if needed
      - Update relationship descriptions
   
   e) API Endpoints Section
      - Add new endpoints to table
      - Update method/path if changed
      - Add request/response examples
      - Document status codes
      - Include curl command examples
   
   f) Technology Stack Section
      - Update version numbers in table
      - Add new dependencies if added
      - Note breaking changes
   
   g) Key Features Section
      - Add ✅ for newly completed features
      - Remove completed items from "Future Enhancements"
      - Update feature descriptions
   
   h) Troubleshooting Section
      - Add new issues discovered
      - Include solutions
      - Reference error logs
   
   i) Last Updated
      - Set to today's date: February 23, 2026
   
   j) Database Section (if changed)
      - Update table descriptions
      - Add new tables
      - Document new columns
      - Migration instructions
   
   k) Future Enhancements Section
      - Update based on completed features
      - Add new planned features
      - Remove completed items

5. FORMATTING REQUIREMENTS:
   - Use Markdown syntax
   - Use ✅ checkmarks for completed items
   - Use 🔄 for in-progress items
   - Use 📋 for planned items
   - Use tables for: endpoints, tech stack, versions
   - Use code blocks with syntax highlighting
   - Use ASCII diagrams for relationships
   - Include curl command examples: curl -X GET http://localhost:8080/api/v1/...
   - Include JSON examples for request/response
   - Use headers hierarchically: # Main, ## Sub, ### Detail
   - Maintain consistent indentation

6. VERIFICATION STEPS:
   - Verify all code examples are syntactically correct
   - Check all links and references work
   - Ensure no outdated information remains
   - Verify version number makes sense
   - Check consistency of formatting throughout
   - Verify all entity changes are documented
   - Confirm all API changes are documented
   - Check dependency versions are current

7. OUTPUT FORMAT:
   - Show changes summary first (3-5 sentences)
   - List all files modified
   - Present updated README.md content
   - Provide commit message suggestion
   - List any follow-up actions needed

COMMIT MESSAGE FORMAT:
"docs: update README with [feature/fix/refactor] - version X.X.X"

Examples:
- "docs: update README with OptimisticLockException fixes - version 0.0.1"
- "docs: update README with new REST endpoints - version 0.1.0"
- "docs: update README with Spring Boot upgrade - version 0.0.2"

QUALITY CHECKLIST:
□ All changed files are listed
□ Version number is semantically correct
□ All API endpoints are documented
□ All new features are explained
□ All bugs fixes are documented
□ All dependencies are listed
□ Examples are provided
□ Formatting is consistent
□ Links are valid
□ Last Updated timestamp is current
□ No outdated information remains
```

---

## 📝 SPECIALIZED PROMPTS

### For Entity/Model Changes Only

```
I've modified JPA entity models in src/main/java/com/echapps/social/media/models/

Scan and update README:

1. Identify:
   - New entity classes
   - Added/removed fields
   - Changed @OneToMany, @ManyToMany, @OneToOne relationships
   - New annotations (@Version, @Transient, etc.)
   - Cascade type changes
   - Fetch type changes
   - Validation annotations

2. Update README sections:
   - Entity Relationships diagram (ASCII art)
   - Entity Descriptions section
   - Database Tables section
   - Document changes and why

3. Include:
   - Before/after entity code samples
   - Database migration SQL if applicable
   - Usage examples
   - Links to Hibernate docs
```

### For API Endpoint Changes Only

```
I've added or modified REST endpoints in src/main/java/com/echapps/social/media/controllers/

Scan and update README:

1. Identify:
   - New @GetMapping, @PostMapping, @PutMapping, @DeleteMapping methods
   - Changed paths or HTTP methods
   - New @RequestParam, @PathVariable, @RequestBody
   - Response type changes
   - New @ExceptionHandler methods
   - HTTP status code changes

2. Update README sections:
   - API Endpoints table
   - Add request/response examples
   - Document status codes
   - Add curl command examples
   - Update example client code

3. Format examples:
   - Include both request and response
   - Show success (200/201) and error (400/404/500) responses
   - Provide curl commands for testing
   - Document all parameters
```

### For Dependency Updates Only

```
I've updated dependencies in pom.xml

Scan and update README:

1. Identify:
   - New dependencies added
   - Version changes
   - Removed dependencies
   - Major version upgrades

2. Update README sections:
   - Technology Stack table (versions)
   - Breaking Changes section
   - Migration Guide section
   - Compatibility notes

3. Include:
   - Old version → New version for updates
   - Why each upgrade was needed
   - Compatibility with Java 21
   - Security fixes notes
   - Configuration changes needed
   - Testing recommendations
```

### For Configuration Changes Only

```
I've modified application.properties and/or application-*.properties files

Scan and update README:

1. Identify:
   - New properties added
   - Property values changed
   - Profile-specific configurations
   - Environment variable mappings
   - Removed properties

2. Update README sections:
   - Application Properties section
   - Environment Variables section
   - Profiles section (dev, test, prod)
   - Performance Tuning section

3. Include:
   - Property name and description
   - Default values
   - Possible values/range
   - Impact of changing
   - Profile-specific examples
   - Security-sensitive properties warning
```

### For Pre-Release (Before Tagging)

```
I'm about to release version X.X.X

Perform complete README verification:

1. Scan entire project for ALL changes since last release
2. Create comprehensive changelog:
   - List all new features
   - List all bug fixes
   - List all refactoring
   - List all dependency updates
   - List all breaking changes
   - Note any deprecations

3. Update README.md completely:
   - Update version to X.X.X
   - Update all code examples
   - Verify all examples work
   - Fix all broken links
   - Update Last Updated date
   - Verify all documentation current
   - Check no outdated info remains

4. Verify:
   - All examples compile
   - All curl commands accurate
   - All links valid
   - Formatting consistent
   - No typos or grammar issues

5. Provide:
   - Release notes
   - Breaking changes list
   - Upgrade guide
   - Commit message
```

---

## 🔄 WORKFLOW RECOMMENDATIONS

**After Each Feature:**
```
1. Test the feature
2. Run "For API Endpoint Changes Only" or "For Entity Changes" prompt
3. Review README changes
4. Commit: git commit -am "docs: update README with new [feature name]"
```

**After Bug Fixes:**
```
1. Run "Master Prompt"
2. Review changes
3. Commit: git commit -am "docs: update README with bug fixes"
```

**Weekly/Sprint Review:**
```
1. Run "Master Prompt" 
2. Review all accumulated changes
3. Update version if significant
4. Commit: git commit -am "docs: weekly README update"
```

**Before Release:**
```
1. Run "For Pre-Release" prompt
2. Review all changes
3. Tag: git tag -a v0.1.0 -m "Release version 0.1.0"
4. Push: git push origin main --tags
```

---

## 📊 VERSION NUMBERING GUIDE

Current: **0.0.1-SNAPSHOT**

Format: **MAJOR.MINOR.PATCH[-SNAPSHOT]**

| Change Type | Version Update | Example |
|-------------|-----------------|---------|
| Breaking changes | MAJOR++ | 0.1.0 → 1.0.0 |
| New features (non-breaking) | MINOR++ | 0.1.0 → 0.2.0 |
| Bug fixes | PATCH++ | 0.1.1 → 0.1.2 |
| Development | Add -SNAPSHOT | 0.1.0-SNAPSHOT |
| Release | Remove -SNAPSHOT | 0.1.0-SNAPSHOT → 0.1.0 |

---

## ✅ QUALITY CHECKLIST

Before committing changes from prompt output:

- [ ] Version number incremented correctly
- [ ] All changed files listed
- [ ] All new features described
- [ ] All bug fixes documented
- [ ] API endpoints updated
- [ ] Entity diagrams updated
- [ ] Examples are accurate
- [ ] No broken links
- [ ] Last Updated date is current
- [ ] Commit message is clear
- [ ] No outdated information remains

---

## 💡 TIPS & BEST PRACTICES

✅ **Copy the ENTIRE prompt** - Include all instructions  
✅ **Review suggestions** - Don't auto-accept everything  
✅ **Test examples** - Verify curl commands work  
✅ **Keep format consistent** - Use same style as existing README  
✅ **Document breaking changes** - Always note incompatibilities  
✅ **Include examples** - Especially for new features  
✅ **Link to docs** - Reference Spring Boot and JPA documentation  
✅ **Commit frequently** - After each significant change  
✅ **Use clear messages** - Describe what changed in commit message  
✅ **Tag releases** - Use git tags for versions  

---

## 📚 RELATED DOCUMENTATION

- **OPTIMISTIC_LOCK_ERROR_ANALYSIS.md** - For transaction/concurrency issues
- **FIX_SUMMARY.md** - For recent fixes applied
- **PROMPT_GUIDE.txt** - For additional prompt templates
- **SETUP_SUMMARY.md** - For setup and workflow guidance

---

**Last Updated:** February 23, 2026  
**Current Version:** 0.0.1-SNAPSHOT  
**Status:** Ready to Use
