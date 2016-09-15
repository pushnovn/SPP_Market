<div class="modal items_add_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="show-items-add.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add item in order</h4>
                </div>
                <div class="modal-body">
                    <input id="items_add_id" name="itemId" type="hidden">
                    <div class="form-group">
                        <label>Name: </label>
                        <input id="items_add_name" type="text" required class="form-control" name="nameItem" disabled>
                    </div>
                    <div class="form-group">
                        <label>Category: </label>
                        <input id="items_add_category_name" type="text" required class="form-control" name="categoryItem" disabled>
                    </div>
                    <div class="form-group">
                        <label>Market name: </label>
                        <input id="items_add_market_name" type="text" required class="form-control" name="nameMarket" disabled>
                    </div>
                    <div class="form-group">
                        <label>Market address: </label>
                        <input id="items_add_market_address" type="text" required class="form-control" name="addressMarket" disabled>
                    </div>
                    <div class="form-group">
                        <label>Company name: </label>
                        <input id="items_add_company_name" type="text" required class="form-control" name="companyName" disabled>
                    </div>
                    <div class="form-group">
                        <label>Count: </label>
                        <input id="items_add_count" type="number" required class="form-control" name="count" value="1">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary add-btn">Order</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

