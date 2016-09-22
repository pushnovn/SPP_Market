<div class="modal items_edit_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="supplier-dashboard-items-edit.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update detail info</h4>
                </div>
                <div class="modal-body">
                    <input id="items_edit_id" name="item.id" type="hidden">
                    <div class="form-group">
                        <label>Name: </label>
                        <input id="items_add_name" type="text" required class="form-control" name="item.name">
                    </div>
                    <div class="form-group">
                        <label>Count in market: </label>
                        <input id="items_add_count_in_market" type="number" required class="form-control" name="item.countInWarehouse">
                    </div>
                    <div class="form-group">
                        <label>Price: </label>
                        <input id="items_add_price" type="number" required class="form-control" name="item.price">
                    </div>
                    <div class="form-group">
                        <label>Category: </label>
                        <select required class="form-control" name="item.category.id">
                            <s:iterator value="categoriesList" var="category">
                                <option value="<s:property value="id"></s:property>">
                                    <s:property value="name"></s:property>
                                </option>
                            </s:iterator>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Market: </label>
                        <select required class="form-control" name="item.market.id">
                            <s:iterator value="marketsList" var="market">
                                <option value="<s:property value="id"></s:property>">
                                    <s:property value="name"></s:property> . Address:
                                    <s:property value="address"></s:property>
                                </option>
                            </s:iterator>
                        </select>
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

