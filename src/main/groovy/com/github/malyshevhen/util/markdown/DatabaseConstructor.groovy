package com.github.malyshevhen.util.markdown


import com.github.malyshevhen.model.Markdown

class DatabaseConstructor {

    void createDatabase(File initFolder) {
        FileUtil.save(
                new Markdown(
                        picture: null,
                        content: DATABASE_TEMPLATE,
                        path: "${initFolder.getAbsolutePath()}/database.md"))
    }

    public DATABASE_TEMPLATE = """
---

database-plugin: basic

---

```yaml:dbfolder
name: war_database
description: Database of "TEN YEARS OF WAR" project
columns:
  __file__:
    key: __file__
    id: __file__
    input: markdown
    label: File
    accessorKey: __file__
    isMetadata: true
    skipPersist: false
    isDragDisabled: false
    csvCandidate: true
    position: 1
    isHidden: false
    sortIndex: -1
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: true
      task_hide_completed: true
      footer_type: none
      persist_changes: false
  __created__:
    key: __created__
    id: __created__
    input: metadata_time
    label: Created
    accessorKey: __created__
    isMetadata: true
    isDragDisabled: false
    skipPersist: false
    csvCandidate: true
    position: 7
    isHidden: false
    sortIndex: -1
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: false
      task_hide_completed: true
      footer_type: none
      persist_changes: false
  __modified__:
    key: __modified__
    id: __modified__
    input: metadata_time
    label: Modified
    accessorKey: __modified__
    isMetadata: true
    isDragDisabled: false
    skipPersist: false
    csvCandidate: true
    position: 8
    isHidden: false
    sortIndex: -1
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: false
      task_hide_completed: true
      footer_type: none
      persist_changes: false
  Folder:
    input: select
    accessorKey: Folder
    key: Folder
    id: Folder
    label: Folder
    position: 2
    skipPersist: false
    isHidden: false
    sortIndex: -1
    width: 242
    options: []
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: false
      task_hide_completed: true
      footer_type: none
      persist_changes: false
      option_source: manual
      content_alignment: text-align-left
      content_vertical_alignment: align-middle
  Type:
    input: select
    accessorKey: Type
    key: Type
    id: Type
    label: Type
    position: 3
    skipPersist: false
    isHidden: false
    sortIndex: -1
    options:
      - { label: "ARCHIVE", value: "archive", color: "hsl(231, 95%, 90%)"}
      - { label: "MODERN", value: "modern", color: "hsl(231, 95%, 90%)"}
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: false
      task_hide_completed: true
      footer_type: none
      persist_changes: false
      option_source: manual
  Events:
    input: tags
    accessorKey: Events
    key: Events
    id: Events
    label: Events
    position: 4
    skipPersist: false
    isHidden: false
    sortIndex: -1
    width: 101
    options: []
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: false
      task_hide_completed: true
      footer_type: none
      persist_changes: false
      option_source: manual
      content_alignment: text-align-center
      content_vertical_alignment: align-middle
  Related_Files:
    input: relation
    accessorKey: Related_Files
    key: Related_Files
    id: Related_Files
    label: Related_Files
    position: 5
    skipPersist: false
    isHidden: false
    sortIndex: -1
    width: 131
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: true
      task_hide_completed: true
      footer_type: none
      persist_changes: false
      related_note_path: Project_Files.md
      bidirectional_relation: true
      relation_color: hsl(214,59%,62%)
      wrap_content: true
      content_alignment: text-align-justify
      content_vertical_alignment: align-middle
  Date:
    input: calendar
    accessorKey: Date
    key: Date
    id: Date
    label: Date
    position: 6
    skipPersist: false
    isHidden: false
    sortIndex: -1
    config:
      enable_media_view: true
      link_alias_enabled: true
      media_width: 100
      media_height: 100
      isInline: false
      task_hide_completed: true
      footer_type: none
      persist_changes: false
config:
  remove_field_when_delete_column: false
  cell_size: normal
  sticky_first_column: false
  group_folder_column: Folder
  remove_empty_folders: false
  automatically_group_files: true
  hoist_files_with_empty_attributes: true
  show_metadata_created: true
  show_metadata_modified: true
  show_metadata_tasks: false
  show_metadata_inlinks: false
  show_metadata_outlinks: false
  show_metadata_tags: false
  source_data: current_folder
  source_form_result: 
  source_destination_path: /
  row_templates_folder: Templates
  current_row_template: 
  pagination_size: 10
  font_size: 16
  enable_js_formulas: false
  formula_folder_path: /
  inline_default: false
  inline_new_position: last_field
  date_format: yyyy-MM-dd
  datetime_format: "yyyy-MM-dd HH:mm:ss"
  metadata_date_format: "yyyy-MM-dd HH:mm:ss"
  enable_footer: false
  implementation: default
filters:
  enabled: false
  conditions:
```
"""
}
