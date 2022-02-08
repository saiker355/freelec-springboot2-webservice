const index = {
    init : function(){
        let _this = this;
        $('#btn-save').on('click', ()=>{
            _this.save();
        })

        $('#btn-update').on('click', ()=>{
            _this.update();
        })

        $('#btn-delete').on('click', ()=>{
                    _this.del();
                })
    },

    save: function(){
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        }

        $.ajax({
            type: 'post',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
            }).done(function(){
                alert('등록됨')
                window.location.href = '/'
            }).fail(function(err){
                alert(JSON.stringify(err))
            })

    },

    update: function(){
        const data = {
            title: $('#title').val(),
            content: $('#content').val()
        }

        const id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('수정됨')
            window.location.href = '/'
        }).fail(function(err){
            alert(JSON.stringify(err))
        })

    },

    del : function(){
        const id = $('#id').val();

        $.ajax({
                    type: 'DELETE',
                    url: '/api/v1/posts/'+id,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(id)
                }).done(function(){
                    alert('삭제됨')
                    window.location.href = '/'
                }).fail(function(err){
                    alert(JSON.stringify(err))
                })

    }

};

index.init();