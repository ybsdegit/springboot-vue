<template>
    <div style="margin-left: 10px">
        <category-bar @categorySelect="getNotes" ref="categoryBar"></category-bar>
        <notes ref="notes" @editInfo="handleEditInfo" @addNote="handleAddNote" @deleteInfo="getNotes"></notes>
        <note-edit-form ref="noteEditForm" @updateInfo="getNotes" ></note-edit-form>
    </div>
</template>

<script>
    import Notes from "./Notes";
    import CategoryBar from "./CategoryBar";
    import NoteEditForm from "./NoteEditForm";
    export default {
        name: "BookShelf",
        components: {CategoryBar, Notes, NoteEditForm},
        data() {
            return {
            }
        },

        methods: {
            getNotes(){
                const _this = this
                const categoryCid = this.$refs.categoryBar.currentCid.toString()
                this.axios.get('/categories/' + categoryCid + '/notes')
                .then(function (response) {
                    if (response.data.status == 200) {
                        _this.$refs.notes.notes = response.data.data
                        _this.$message.success(response.data.msg);
                    }
                    console.log(_this.$refs.notes.notes)
                })
            },
            handleEditInfo(noteInfo){
                this.$refs.noteEditForm.dialogFormVisible = true
                this.$refs.noteEditForm.form = noteInfo
                this.$refs.noteEditForm.isCreate = false
            },
            handleAddNote(){
                const categoryCid = this.$refs.categoryBar.currentCid.toString()
                this.$refs.noteEditForm.dialogFormVisible = true
                this.$refs.noteEditForm.isCreate = true
                this.$refs.noteEditForm.form = {
                    category: categoryCid
                }
            },
        }
    }
</script>

<style scoped>

</style>