// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
(function($,cloudstack) {
  cloudStack.autoscaler = {
    // --
    // Add the following object blocks:
    //
    // topFields: { <standard createForm field format> }
    // bottomFields: { <standard createForm field format> },
    // scaleUpPolicy: { <standard multiEdit field format> },
    // scaleDownPolicy: { <standard multiEdit field format> }
    // --
    //
        forms: {
		topFields: {
       		    templateCategory: {
                      label: 'Template Category',
                      select: function(args) {
                            args.response.success({
                              data: [
                                { category: 'all', description: _l('ui.listView.filters.all') },
                                { category: 'featured', description: _l('label.featured') },
                                { category: 'Community', description: _l('label.menu.community.templates') },
                                { category: 'self', description: _l('ui.listView.filters.mine') }
                              ]
                            });
                          }
                    },

                    templateNames: {
                      label: 'Template Name',
                      select: function(args) {
                        $.ajax({
                          url: createURL("listTemplates&templatefilter=all" ),
                          dataType: "json",
                          async: true,
                          success: function(json) {
                            var templates = json.listtemplatesresponse.template;
                            args.response.success({
                                data:  $.map(templates, function(template) {
                                return {
                                id: template.id,
                                description: template.name
                              };
                            })
                            });
                          }
                        });
                      }
                    },

                    serviceOfferingId: {
                      label: 'label.compute.offering',
                      select: function(args) {
                        $.ajax({
                          url: createURL("listServiceOfferings&issystem=false"),
                          dataType: "json",
                          async: true,
                          success: function(json) {
                            var serviceofferings = json.listserviceofferingsresponse.serviceoffering;
                            args.response.success({
                            	data:  $.map(serviceofferings, function(serviceoffering) {
                                return {
                                id: serviceoffering.id,
                                description: serviceoffering.name
                                };
                            	})
                            });
                          }
                        });
                      }
                    },

                    minInstance: {
                    	label: 'Min Instances',
                    	defaultValue: '3',
                    	validation: { required: true }
                    },

                    maxInstance: {
                    	label: 'Max Instances',
                    	defaultValue: '10',
                    	validation: { required: true }
                    }
		},
		
		bottomFields: {
                    quietTime: {
                    	label: 'Quiet Time (in sec)',
                    	defaultValue: '300',
                    	validation: { required: true }
                    },

                    destroyVMgracePeriod: {
                    	label: 'Destroy VM Grace Period',
                    	defaultValue: '30',
                    	validation: { required: true }
                    },

                    interval: {
                    	label: 'Interval (in sec)',
                    	defaultValue: '30',
                    	validation: { required: true }
                    },

                    securityGroups: {
                    	label: 'label.menu.security.groups',
                    	select: function(args) {
                        	$.ajax({
                        		url: createURL("listSecurityGroups&listAll=true"),
                        		dataType: "json",
                        		async: true,
                        		success: function(json) {
                        			var securitygroups = json.listsecuritygroupsresponse.securitygroup;
                        			args.response.success({
                        				data:  $.map(securitygroups, function(securitygroup) {
                        					return {
                        						id: securitygroup.id,
                        						description: securitygroup.name
                        					};
                        				})
                        			});
                        	}
                        	});
                    }
                  },

                  DiskOfferings: {
                    label: 'label.menu.disk.offerings',
                    select: function(args) {
                        $.ajax({
                          url: createURL("listDiskOfferings&listAll=true"),
                          dataType: "json",
                          async: true,
                          success: function(json) {
                            var diskofferings = json.listdiskofferingsresponse.diskoffering;
                            args.response.success({
                                data:  $.map(diskofferings, function(diskoffering) {
                                return {
                                id: diskoffering.id,
                                description: diskoffering.name
                              };
                              })
                            });
                          }
                        });
                    }
                  },
                  
                  snmpCommunity: {
                    label: 'SNMP Community',
                    defaultValue: 'Public',
                    validation: { required: true }
                  },

                  snmpPort: {
                    label: 'SNMP Port',
                    defaultValue: '161',
                    validation: { required: true }
                  },

                  username: {
                    label: 'Username',
                    /*select: function(args) {
                        $.ajax({
                          url: createURL("listUsers&domainid=" + args.context.users[0].domainid),
                          dataType: "json",
                          async: true,
                          success: function(json) {
                            var users = json.listusersresponse.user;
                            var items = [];
                            args.response.success({
                                data:  $.map(users, function(user) {
                                return {
                                id: user.id,
                                description: user.username
                              };
                              })
                            });
                          }
                        });
                    }*/
                    
                  }
		},
		scaleUpPolicy: {
            title: 'ScaleUp Policy',
              noSelect: true,
              noHeaderActionsColumn: true,
              fields: {
                'name': { edit: true, label: 'Counter' },
                'operator': {
                	label: 'Operator',
                	select: function(args) {
                    args.response.success({
                        data: [
                          { category: 'greater-than', description: _l('greater-than') },
                          { category: 'equals-to', description: _l('equals-to') }
                        ]
                      });
                    }
                },
                'threshold': { edit: true, label: 'Threshold'},
                'add-scaleUpcondition': {
                  label: 'label.add',
                  addButton: true
                }
              },
              add: {
                label: 'label.add',
                action: function(args) {
                  $.ajax({
                    url: createURL(''),
                    dataType: 'json',
                    async: false
                  });
                }
              },
              actions: {
                destroy: {
                  label: '',
                  action: function(args) {
                    $.ajax({
                      url: createURL(''),
                      dataType: 'json',
                      async: true,
                    });
                  }
                }
              },
              dataProvider: function(args) {
                $.ajax({
                  url: createURL('listClusters'),
                  dataType: 'json',
                  async: true,
                  success: function(json) {
                    args.response.success({
                    	data: json.listclustersresponse.cluster
                    });
                  }
                });
              }
          },
          
  		scaleDownPolicy: {
              	title: 'ScaleDown Policy',
                noSelect: true,
                noHeaderActionsColumn: true,
                fields: {
                  'name': { edit: true, label: 'Counter' },
                  'operator': {
                  	label: 'Operator',
                  	select: function(args) {
                      args.response.success({
                          data: [
                            { category: 'less-than', description: _l('less-than') },
                            { category: 'equals-to', description: _l('equals-to') }
                          ]
                        });
                      }
                  },
                  'threshold': { edit: true, label: 'Threshold'},
                  'add-scaleDowncondition': {
                    label: 'label.add',
                    addButton: true
                  }
                },
                add: {
                  label: 'label.add',
                  action: function(args) {
                    $.ajax({
                      url: createURL(''),
                      dataType: 'json',
                      async: false
                    });
                  }
                },
                actions: {
                  destroy: {
                    label: '',
                    action: function(args) {
                      $.ajax({
                        url: createURL(''),
                        dataType: 'json',
                        async: true,
                      });
                    }
                  }
                },
                dataProvider: function(args) {
                  $.ajax({
                    url: createURL('listClusters'),
                    dataType: 'json',
                    async: true,
                    success: function(json) {
                      args.response.success({
                      	data: json.listclustersresponse.cluster
                      });
                    }
                  });
                }
            }
	},
    dialog: function(args) {
      return function(args) { 
        var context = args.context; 

        var $dialog= $('<div>');
        $dialog.dialog ({
          title: 'AutoScale Configuration Wizard',
          closeonEscape: false,

          draggable:true,
          width:  825 ,
          height :600,
          buttons: {
            'Cancel': function() {
              $(this).dialog("close");
              $('.overlay').remove();
            },


            'Apply': function() {
              $(':ui-dialog').remove();
              $('.overlay').remove();
            }
          }
        }).closest('.ui-dialog').overlay();

        $("buttons").each(function() {
          $(this).attr('style','float: right');
        });
        var $field = $('<div>').addClass('field username');
        var $input = $('<input>').attr({ name: 'username' });
        var $inputLabel = $('<label>').html('Username');

        $field.append($input, $inputLabel);
        $field.appendTo($dialog);


      }       
    }
  }
} (jQuery,cloudStack));
