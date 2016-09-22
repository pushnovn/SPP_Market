<div class="modal arkets_edit_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="adminpanel-markets-update.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update market info</h4>
                </div>
                <div class="modal-body">
                    <input id="markets_edit_id" name="market.id" type="hidden">
                    <div class="form-group">
                        <label>Name: </label>
                        <input id="markets_edit_name" type="text" required class="form-control" name="market.name">
                    </div>
                    <div class="form-group">
                        <label>Address: </label>
                        <input id="markets_edit_address" type="text" required class="form-control" name="market.address">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary add-btn">Save changes</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
